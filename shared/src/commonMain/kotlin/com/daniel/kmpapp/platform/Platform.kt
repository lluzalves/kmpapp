package com.daniel.kmpapp.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
