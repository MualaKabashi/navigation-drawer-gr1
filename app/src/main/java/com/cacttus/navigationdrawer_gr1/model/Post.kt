package com.cacttus.navigationdrawer_gr1.model

import com.google.gson.annotations.SerializedName

data class Post(
    var userId: Int,
    var id:Int,
    var title:String,
    @SerializedName("body")
    var description:String
)
