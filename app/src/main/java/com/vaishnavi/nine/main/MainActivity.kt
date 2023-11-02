package com.vaishnavi.nine.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vaishnavi.nine.R
import com.vaishnavi.nine.ui.ErrorScreen
import com.vaishnavi.nine.ui.LoadingScreen
import com.vaishnavi.nine.ui.MainScreen
import com.vaishnavi.nine.web.WebActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel =
            ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            LoadingScreen(alpha = 1f) //show loading screen
        }

        lifecycleScope.launch {
            //collect the response from viewmodel
            mainViewModel.response.collect() { response ->
                setContent {
                    if (response?.assets?.isEmpty() == true) {
                        ErrorScreen() //show error screen if empty response
                    } else {
                        response?.assets?.sortedByDescending { response.timeStamp }.let {
                            if (it != null) {
                                response?.timeStamp?.let { timestamp ->
                                    MainScreen(timestamp, it) { url -> //open webview when clicked on the article
                                        val intent = Intent(this@MainActivity, WebActivity::class.java)
                                        intent.putExtra(
                                            resources.getString(R.string.intent_label_url),
                                            url
                                        )
                                        startActivity(intent)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getDataFromServer() //fetch data from remote
    }
}