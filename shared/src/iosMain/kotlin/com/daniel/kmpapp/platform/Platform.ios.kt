package com.daniel.kmpapp.platform

import com.daniel.kmpapp.IOSWebPageRouter
import com.daniel.kmpapp.WebPageRouter
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
