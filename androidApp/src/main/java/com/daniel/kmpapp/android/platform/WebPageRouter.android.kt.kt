package com.daniel.kmpapp.android.platform

import android.content.Intent
import android.net.Uri
import com.daniel.kmpapp.WebPageRouter

class AndroidWebPageRouter() : WebPageRouter {
    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = Uri.parse(url)
        }
    }
}