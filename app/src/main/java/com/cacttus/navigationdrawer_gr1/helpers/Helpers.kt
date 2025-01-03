package com.cacttus.navigationdrawer_gr1.helpers

import com.cacttus.navigationdrawer_gr1.api.ServiceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Helpers {
    fun provideRetrofit() : ServiceApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(ServiceApi::class.java)
    }
}