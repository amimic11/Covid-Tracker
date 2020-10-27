package com.graveno.alphalab.covidtracker.fragments.countrystatesdetail

import android.annotation.SuppressLint
import android.app.Application
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.AndroidViewModel
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity

class CountryStatesDetailViewModel(application: Application) : AndroidViewModel(application) {
    val TAG : String = "CountryStatesDetailViewModel"

    @SuppressLint("SetJavaScriptEnabled")
    fun loadStatesDetail(mainActivity: MainActivity, webView: WebView) {
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.displayZoomControls = true
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(mainActivity.getString(R.string.india_covid_detail))
        webView.webViewClient = WebViewClient()
    }
}
