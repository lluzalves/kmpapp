package com.daniel.kmpapp

interface WebPageRouter{
    fun openUrl(url :String)
}

expect fun openWebPage() : WebPageRouter