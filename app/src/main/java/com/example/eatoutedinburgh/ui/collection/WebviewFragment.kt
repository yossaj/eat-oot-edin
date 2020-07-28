package com.example.eatoutedinburgh.ui.collection

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.eatoutedinburgh.R
import kotlinx.android.synthetic.main.fragment_webview.*


class WebviewFragment : Fragment() {

    lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString("url")?.let {
            url = it
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setUpWebView(){
        collection_webview?.let {
            it.settings.useWideViewPort = true
            it.settings.javaScriptEnabled = true
            it.webChromeClient = object : WebChromeClient(){
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
//                TODO:: Add progressbar
                    super.onProgressChanged(view, newProgress)
                }
            }
            it.webViewClient = object : WebViewClient(){

            }
            it.loadUrl(url)
        }
        }


}