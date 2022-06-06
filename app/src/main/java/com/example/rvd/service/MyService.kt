package com.example.rvd.service

import com.example.rvd.model.GameNFT
import retrofit2.Call
import retrofit2.http.GET

interface MyService {
    @GET("/assets")
    fun getGame() : Call<GameNFT>
}