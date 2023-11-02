package com.vaishnavi.nine.web

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.vaishnavi.nine.R
import com.vaishnavi.nine.ui.LoadingScreen
import com.vaishnavi.nine.ui.WebScreen

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { //load the webview screen
            LoadingScreen(1f)
            val url = intent.getStringExtra(resources.getString(R.string.intent_label_url))
            WebScreen(url)
        }

    }
}