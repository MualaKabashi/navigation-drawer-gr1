package com.cacttus.navigationdrawer_gr1.api

import com.cacttus.navigationdrawer_gr1.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {
    @GET("posts")
    fun getPost(): Call<List<Post>>
}