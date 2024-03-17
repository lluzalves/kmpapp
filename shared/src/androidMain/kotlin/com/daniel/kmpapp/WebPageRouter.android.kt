package com.daniel.kmpapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.daniel.kmpapp.di.KoinManager

class AndroidWebPageRouter : WebPageRouter {
    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = Uri.parse(url)
        }
        KoinManager().getKoinInstance().get<Context>().apply {
            startActivity(intent)
        }
    }
}

actual fun openWebPage() : WebPageRouter = AndroidWebPageRouter()