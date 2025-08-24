package pl.kacper.misterski.multiplatform.core

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

class PermissionHandlerImpl :PermissionHandler {

     override fun openAppSettings() {
        val settingsUrl = NSURL.URLWithString(UIApplicationOpenSettingsURLString)
        settingsUrl?.let { url ->
            if (UIApplication.sharedApplication.canOpenURL(url)) {
                UIApplication.sharedApplication.openURL(url)
            } else {
                println("Cannot open iOS App Settings URL: $url")
            }
        }
    }

}