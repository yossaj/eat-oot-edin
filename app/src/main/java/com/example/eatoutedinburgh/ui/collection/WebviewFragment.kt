package com.example.eatoutedinburgh.ui.collection

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.eatoutedinburgh.R
import kotlinx.android.synthetic.main.fragment_webview.*


class WebviewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setUpWebView(){
        collection_webview.settings.useWideViewPort = true
        collection_webview.settings.javaScriptEnabled = true
        collection_webview.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
//                TODO:: Add progressbar
                super.onProgressChanged(view, newProgress)
            }
        }
        collection_webview.webViewClient = object : WebViewClient(){


        }
        val url = "www.google.com"
        collection_webview.loadUrl(url)
    }

}