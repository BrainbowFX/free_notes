package com.brainbowfx.android.simplenotes.presentation.utils

interface PermissionManager {

    var grantedPermissionsCallbacks: MutableMap<String, (String) -> Unit>
    var deniedPermissionsCallbacks: MutableMap<String, (String) -> Unit>
    fun checkPermission(
        permission: String,
        onPermissionGranted: (String) -> Unit,
        onPermissionDenied: (String) -> Unit
    )
}