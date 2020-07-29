package com.example.eatoutedinburgh.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurant(

    var id : Int,
    var name : String,
    var url : String,
    @SerializedName("timings")
    @Expose
    var businessHours : String,
    var locations : String?,
    @SerializedName("featured_image")
    @Expose
    var featuredImage : String?,
    @SerializedName("photo_url")
    @Expose
    var photoUrl : String?,
    @SerializedName("thumb")
    @Expose
    var thumbnail : String
)