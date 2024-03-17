package com.daniel.kmpapp

import androidx.compose.ui.window.ComposeUIViewController
import com.daniel.kmpapp.di.KoinManager
import com.daniel.kmpapp.presentation.RedirectScreen

fun MainController() = ComposeUIViewController {
    KoinManager.startKoin()
    RedirectScreen()
}