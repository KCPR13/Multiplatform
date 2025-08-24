package pl.kacper.misterski.multiplatform.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
class PermissionHandlerImpl(private val context: Context) : PermissionHandler {

    override fun openAppSettings() {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", context.packageName, null)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}