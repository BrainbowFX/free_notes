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
import com.brainbowfx.android.freenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.router.NotesEditRouter
import com.brainbowfx.android.freenotes.presentation.App
import com.brainbowfx.android.freenotes.presentation.utils.PermissionManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Runnable
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), PermissionManager, ImageViewer {

    companion object {
        private const val REQUEST_PERMISSION_CODE = 1
        private const val FAB_ANIMATION_DELAY = 120L
    }

    override var grantedPermissionsCallbacks: MutableMap<String, (String) -> Unit> = mutableMapOf()
    override var deniedPermissionsCallbacks: MutableMap<String, (String) -> Unit> = mutableMapOf()

    private var isSaveInstanceStateCalled = false

    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var floatingActionButton: FloatingActionButton

    private var iconBack: Drawable? = null
    private var iconAdd: Drawable? = null

    private var addNoteClickListener = View.OnClickListener { notesEditRouter.navigateNext() }
    private var returnBackClickListener = View.OnClickListener { notesEditRouter.returnBack() }

    @Inject
    lateinit var notesEditRouter: NotesEditRouter

    @Inject
    lateinit var urlToUriMapper: Mapper<String, Uri>

    @Inject
    lateinit var dispatchersProvider: CoroutineDispatchersProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.Instance.plusActivityPerInstanceSubComponent()
        App.Instance.plusActivitySubComponent(this)?.inject(this)

        iconBack = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
        iconAdd = ContextCompat.getDrawable(this, R.drawable.ic_add)

        bottomAppBar = findViewById(R.id.bottomAppBar)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener(addNoteClickListener)

        notesEditRouter.addCallback {
            when (it) {
                R.id.notesListFragment -> setFabButtonStateAdd()
                R.id.notesEditFragment -> setFabButtonStateBack()
            }
        }
    }

    private fun setFabButtonStateAdd() {
        floatingActionButton.postDelayed(
            {
                bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                floatingActionButton.setOnClickListener(addNoteClickListener)
                floatingActionButton.setImageDrawable(iconAdd)
            },
            FAB_ANIMATION_DELAY
        )
    }

    private fun setFabButtonStateBack() {
        floatingActionButton.postDelayed(
            {
                bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                floatingActionButton.setOnClickListener(returnBackClickListener)
                floatingActionButton.setImageDrawable(iconBack)
            },
            FAB_ANIMATION_DELAY
        )
    }

    override fun onBackPressed() {
        if (!notesEditRouter.returnBack()) finish()
    }

    //Lifycycle methods
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isSaveInstanceStateCalled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        App.Instance.clearActivitySubComponent()
        if (!isSaveInstanceStateCalled) App.Instance.clearActivityPerInstanceSubComponent()
    }

    //PermissionManager implementation methods
    override fun checkPermission(
        permission: String,
        onPermissionGranted: (String) -> Unit,
        onPermissionDenied: (String) -> Unit
    ) {
        grantedPermissionsCallbacks[permission] = onPermissionGranted
        deniedPermissionsCallbacks[permission] = onPermissionDenied

        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_PERMISSION_CODE)
        } else {
            onPermissionGranted.invoke(permission)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

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
