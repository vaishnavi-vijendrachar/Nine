package com.vaishnavi.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("assets") var assets: List<Assets> = listOf(),
    @SerializedName("timeStamp") var timeStamp: Long? = null,
)

data class RelatedImages(
    @SerializedName("url") var url: String? = null,
)

data class Assets(
    @SerializedName("url") var url: String? = null,
    @SerializedName("byLine") var byLine: String,
    @SerializedName("headline") var headline: String,
    @SerializedName("relatedImages") var relatedImages: List<RelatedImages> = listOf(),
    @SerializedName("theAbstract") var theAbstract: String,
)