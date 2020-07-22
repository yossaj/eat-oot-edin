package com.example.eatoutedinburgh.data.apiservice.responses

import com.example.eatoutedinburgh.data.models.Collection
import com.example.eatoutedinburgh.data.models.CollectionList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class CollectionListResponse(
    @SerializedName("collections")
    @Expose
    val collections : List<CollectionList>
)