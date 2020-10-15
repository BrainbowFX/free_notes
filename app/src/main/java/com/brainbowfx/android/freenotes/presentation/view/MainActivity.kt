package com.brainbowfx.android.freenotes.presentation.view

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.brainbowfx.android.freenotes.PREF_DARK_THEME
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.router.NotesRouter
import com.brainbowfx.android.freenotes.presentation.App
import com.brainbowfx.android.freenotes.presentation.abstraction.FloatingActionButtonOwner
import com.brainbowfx.android.freenotes.presentation.abstraction.PermissionManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), PermissionManager, ImageViewer, FloatingActionButtonOwner {

    companion object {
        private const val REQUEST_PERMISSION_CODE = 1
    }

    override var grantedPermissionsCallbacks: MutableMap<String, (String) -> Unit> = mutableMapOf()
    override var deniedPermissionsCallbacks: MutableMap<String, (String) -> Unit> = mutableMapOf()

    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var floatingActionButton: FloatingActionButton

    @Inject
    lateinit var urlToUriMapper: Mapper<String, Uri>

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.Instance.activitySubComponent?.inject(this)

        floatingActionButton = findViewById(R.id.floatingActionButton)
        setupBottomNavBar()
    }

    private fun setupBottomNavBar() {
        bottomAppBar = findViewById(R.id.bottomAppBar)
        bottomAppBar.menu
            .add("")
            .setIcon(
                if (preferences.getInt(
                        PREF_DARK_THEME,
                        AppCompatDelegate.MODE_NIGHT_NO
                    ) == AppCompatDelegate.MODE_NIGHT_NO
                ) {
                    R.drawable.ic_day
                } else {
                    R.drawable.ic_night
                }
            )
            .setOnMenuItemClickListener {
                switchTheme()
                true
            }
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
    }

    private fun switchTheme() {
        val iconWithTheme = if (preferences.getInt(
                PREF_DARK_THEME,
                AppCompatDelegate.MODE_NIGHT_NO
            ) == AppCompatDelegate.MODE_NIGHT_NO
        ) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        preferences.edit().putInt(PREF_DARK_THEME, iconWithTheme).apply()
        window.setWindowAnimations(R.style.WindowAnimationTransition)
        AppCompatDelegate.setDefaultNightMode(iconWithTheme)
    }

    //PermissionManager implementation
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

    //ImageViewer implementation
    override fun showImage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).also {
            it.setDataAndType(urlToUriMapper.map(url), "image/*")
            it.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        intent.resolveActivity(packageManager)?.let {
            startActivity(intent)
        }
    }

    //FloatingActionButtonOwner implementation
    override fun setupButton(
        drawableRes: Int,
        fabAlignmentMode: Int,
        onClickListener: View.OnClickListener
    ) {
        bottomAppBar.fabAlignmentMode = fabAlignmentMode
        floatingActionButton.setOnClickListener(onClickListener)
        floatingActionButton.setImageResource(drawableRes)
    }
}
