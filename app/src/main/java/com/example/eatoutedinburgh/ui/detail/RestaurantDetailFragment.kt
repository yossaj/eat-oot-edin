package com.example.eatoutedinburgh.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.eatoutedinburgh.databinding.FragmentRestaurantDetailBinding
import com.example.eatoutedinburgh.viewmodels.main.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*


class RestaurantDetailFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels<MainViewModel>()
    lateinit var url : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRestaurantDetailBinding.inflate(inflater)
        viewModel.restaurantDetail.observe(viewLifecycleOwner, Observer {
           binding.restaurant = it
            if(!it.photosUrl.isNullOrBlank()){
                url = it.photosUrl!!
                setUpWebView(binding)
            }

            if(it.thumbnail.isNotBlank()){
                Picasso.get().load(it.featuredImage).into(binding.detailRestaurantImage)
            }

        })



        return binding.root
    }


    @SuppressLint("SetJavaScriptEnabled")
    fun setUpWebView(binding: FragmentRestaurantDetailBinding) {
        binding.restaurantPictures.let {
            it.visibility = View.INVISIBLE
            it.settings.useWideViewPort = true
            it.settings.javaScriptEnabled = true
            it.webChromeClient = object : WebChromeClient(){
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
//                TODO:: Add progressbar
                    super.onProgressChanged(view, newProgress)
                }
            }
            it.webViewClient = object : WebViewClient(){

                override fun onPageFinished(view: WebView?, url: String?) {
                    it.loadUrl("javascript:(function() { " +
                            "document.getElementsByTagName('footer')[0].style.display=\"none\"; " +
                            "document.getElementsByTagName('header')[0].style.display=\"none\"; " +
                            "document.getElementsByTagName('section')[6].style.display=\"none\"; " +
                            "document.getElementsByTagName('h1')[0].style.display=\"none\"; " +
                            "document.getElementsByClassName('sc-fQkuQJ fChYwX')[0].style.display=\"none\"; " +
                            "document.getElementsByTagName('article')[0].style.display=\"none\"; " +
                    "})()")
                    it.visibility = View.VISIBLE
                    super.onPageFinished(view, url)
                }

            }
            it.loadUrl(url)
        }
    }


}