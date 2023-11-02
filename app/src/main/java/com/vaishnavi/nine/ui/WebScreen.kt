package com.vaishnavi.nine.ui

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebScreen(url: String?) {
    MaterialTheme {
        Scaffold(
            topBar = { TopBar() },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(padding)
                        .background(Color.Black)
                ) {

                    var backEnabled by remember { mutableStateOf(false) }
                    var webView: WebView? = null
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                webViewClient = object : WebViewClient() {
                                    override fun onPageStarted(
                                        view: WebView,
                                        url: String?,
                                        favicon: Bitmap?
                                    ) {
                                        backEnabled = view.canGoBack()
                                    }
                                }
                                settings.javaScriptEnabled = true
                                if (url != null) {
                                    loadUrl(url)
                                }
                                webView = this
                            }
                        }, update = { web ->
                            webView = web
                        })

                    BackHandler(enabled = backEnabled) {
                        webView?.goBack()
                    }
                }
            }
        )
    }
}