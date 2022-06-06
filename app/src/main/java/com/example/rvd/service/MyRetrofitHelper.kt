package com.example.rvd.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitHelper {
    private var retrofit: Retrofit? = null
    val BASE_URL = "https://api.opensea.io/api/v1/"
    val apiService: MyService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(MyService::class.java)
        }
}