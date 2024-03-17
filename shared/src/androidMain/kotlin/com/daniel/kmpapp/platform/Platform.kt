package com.daniel.kmpapp.platform

import com.daniel.kmpapp.AndroidWebPageRouter
import com.daniel.kmpapp.WebPageRouter
import org.koin.core.module.Module
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
