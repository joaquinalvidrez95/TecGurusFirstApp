package com.joaquinalvidrez.tecgurusfirstapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import com.joaquinalvidrez.tecgurusfirstapp.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        web_view_car.apply {
            webViewClient = WebViewClient()
            settings.apply {
                javaScriptEnabled = true
                builtInZoomControls = true
            }
            loadUrl("http://square.github.io/picasso/")
        }


    }
}
