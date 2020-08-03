package com.example.eatoutedinburgh.ui.collection

import android.annotation.SuppressLint
import android.graphics.Bitmap
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

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {

                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    it.loadUrl("javascript:(function() { " +
                            "document.getElementsByTagName('header')[0].style.display=\"none\"; " +
                            "})()")
                    it.visibility = View.VISIBLE
                    super.onPageFinished(view, url)
                }

                override fun onPageCommitVisible(view: WebView?, url: String?) {
                    webview_progress_bar.visibility = View.GONE
                    super.onPageCommitVisible(view, url)
                }

            }
            it.loadUrl(url)

        }
        }


}