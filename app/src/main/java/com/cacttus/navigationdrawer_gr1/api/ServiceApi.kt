package com.cacttus.navigationdrawer_gr1.api

import com.cacttus.navigationdrawer_gr1.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("posts")
    fun getPost(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>
}