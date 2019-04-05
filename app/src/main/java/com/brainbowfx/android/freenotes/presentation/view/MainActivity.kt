package com.brainbowfx.android.freenotes.presentation.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.abstraction.CameraController
import com.brainbowfx.android.freenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.router.Router
import com.brainbowfx.android.freenotes.presentation.App
import com.brainbowfx.android.freenotes.presentation.utils.PermissionManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), PermissionManager, CameraController, ImageViewer {

    private val REQUEST_PERMISSION_CODE = 1
    private val REQUEST_TAKE_PHOTO_CODE = 2

    override var onCameraResult: ((success: Boolean) -> Unit)? = null

    override var grantedPermissionsCallbacks: MutableMap<String, (String) -> Unit> = mutableMapOf()
    override var deniedPermissionsCallbacks: MutableMap<String, (String) -> Unit> = mutableMapOf()

    private var isSaveInstanceStateCalled = false

    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var floatingActionButton: FloatingActionButton

    private var iconBack: Drawable? = null
    private var iconAdd: Drawable? = null

    private var addNoteClickListener = View.OnClickListener { router.navigateToNotesEdit() }
    private var returnBackClickListener = View.OnClickListener { router.returnBack() }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var urlToUriMapper: Mapper<String, Uri>

    @Inject
    lateinit var dispatchersProvider: CoroutineDispatchersProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.Instance.plusActivitySubcomponent()
        App.Instance.plusActivityPerInstanceSubcomponent(this)?.inject(this)

        iconBack = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
        iconAdd = ContextCompat.getDrawable(this, R.drawable.ic_add)

        bottomAppBar = findViewById(R.id.bottomAppBar)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener(addNoteClickListener)

        router.addCallback {
            when (it) {
                R.id.notesListFragment -> setFabButtonStateAdd()
                R.id.notesEditFragment -> setFabButtonStateBack()
            }
        }
    }

    private fun setFabButtonStateAdd() {
        runWithDelay(120) { bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER }
        floatingActionButton.setOnClickListener(addNoteClickListener)
        floatingActionButton.setImageDrawable(iconAdd)
    }

    private fun setFabButtonStateBack() {
        runWithDelay(120) { bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END }
        floatingActionButton.setOnClickListener(returnBackClickListener)
        floatingActionButton.setImageDrawable(iconBack)
    }

    private fun runWithDelay(delay: Long, run: () -> Unit) {
        GlobalScope.launch(dispatchersProvider.getMainDispatcher()) {
            delay(delay)
            run()
        }
    }

    override fun onBackPressed() {
        if (!router.returnBack()) finish()
    }

    //Lifycycle methods
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        isSaveInstanceStateCalled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        App.Instance.clearActivityPerInstanceSubcomponent()
        if (!isSaveInstanceStateCalled) App.Instance.clearActivitySubcomponent()
    }

    //PermissionManager implementation methods
    override fun checkPermission(
        permission: String,
        onPermissionGranted: (String) -> Unit,
        onPermissionDenied: (String) -> Unit
    ) {
        grantedPermissionsCallbacks[permission] = onPermissionGranted
        deniedPermissionsCallbacks[permission] = onPermissionDenied

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_PERMISSION_CODE)
        } else {
            onPermissionGranted.invoke(permission)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val permission = permissions[0]
                grantedPermissionsCallbacks[permission]?.invoke(permission)
            } else {
                val permission = permissions[0]
                deniedPermissionsCallbacks[permission]?.invoke(permission)
            }
        }
    }

    //CameraController implementation methods
    override fun takePhoto(url: String, onResult: ((success: Boolean) -> Unit)?) {
        onCameraResult = onResult
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)?.also {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, urlToUriMapper.map(url))
            startActivityForResult(intent, REQUEST_TAKE_PHOTO_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> onCameraResult?.invoke(true)
                Activity.RESULT_CANCELED -> onCameraResult?.invoke(false)
                else -> onCameraResult?.invoke(false)
            }
        }
    }

    //ImageViewer implementation methods
    override fun showImage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).also {
            it.setDataAndType(urlToUriMapper.map(url), "image/*")
            it.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        intent.resolveActivity(packageManager)?.let {
            startActivity(intent)
        }
    }
}