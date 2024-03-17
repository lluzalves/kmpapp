package com.daniel.kmpapp

import com.daniel.kmpapp.platform.Platform
import com.daniel.kmpapp.platform.getPlatform

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}