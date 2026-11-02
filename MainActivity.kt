package com.ss.apkbuilder

import android.app.Activity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.chaquo.python.Python

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val web = WebView(this)
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.allowFileAccess = true
        web.webChromeClient = WebChromeClient()
        web.webViewClient = WebViewClient()
        web.addJavascriptInterface(SSBridge(), "SSNative")
        setContentView(web)
        web.loadUrl("file:///android_asset/index.html")
    }

    inner class SSBridge {
        @JavascriptInterface
        fun runPython(source: String): String {
            return try {
                Python.getInstance()
                    .getModule("runner")
                    .callAttr("run_code", source)
                    .toString()
            } catch (e: Exception) {
                "Python error: ${e.message ?: e.javaClass.simpleName}"
            }
        }

        @JavascriptInterface
        fun getNativeInfo(): String {
            return "Kotlin: ${NativeDemo.message()}\\nJava: ${JavaDemo.message()}"
        }
    }
}
