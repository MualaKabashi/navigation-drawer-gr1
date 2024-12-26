package com.cacttus.navigationdrawer_gr1.helpers

import com.cacttus.navigationdrawer_gr1.api.ServiceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Helpers {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun provideRetrofit() : ServiceApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServiceApi::class.java)
    }
}