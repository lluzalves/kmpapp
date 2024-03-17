package com.daniel.kmpapp


import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class IOSWebPageRouter : WebPageRouter {
    override fun openUrl(url: String) {
       UIApplication.sharedApplication().openURL(NSURL(string = url))
    }
}

actual fun openWebPage() : WebPageRouter = IOSWebPageRouter()