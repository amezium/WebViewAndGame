package com.glu.testwork.presentation

import android.annotation.SuppressLint
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewSettings {
    @SuppressLint("SetJavaScriptEnabled")
    fun setSettings(webView: WebView) {
        with(webView.settings) {
            webView.loadUrl("https://fex.net/")
            javaScriptEnabled = true
            allowFileAccess = true
            domStorageEnabled = true
            databaseEnabled = true
            webView.webViewClient = WebViewClient()
            allowFileAccessFromFileURLs = true
            allowContentAccess = true
            CookieManager.getInstance().setAcceptCookie(true)
            pluginState = WebSettings.PluginState.ON
            useWideViewPort = true
            cacheMode = WebSettings.LOAD_DEFAULT
            allowUniversalAccessFromFileURLs = true
            loadWithOverviewMode = true
            javaScriptCanOpenWindowsAutomatically = true
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            setSupportMultipleWindows(false)
            builtInZoomControls = true
            displayZoomControls = false
        }
    }
}