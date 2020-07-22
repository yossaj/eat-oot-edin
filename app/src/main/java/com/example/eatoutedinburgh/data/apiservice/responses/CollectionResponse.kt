package com.example.eatoutedinburgh.data.apiservice.responses

import com.example.eatoutedinburgh.data.models.Collection
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CollectionResponse(
    @SerializedName("collection")
    @Expose
    val collections : Collection
)