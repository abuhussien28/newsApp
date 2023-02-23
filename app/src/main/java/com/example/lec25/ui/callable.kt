package com.example.lec25.ui

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface callable {
    @GET("/v2/top-headlines?apiKey=98562d75b4c544c99b0b9a850e55e887")
    fun getnews(
        @Query("category") cat: String,
        @Query("country") code: String
    ): Call<News>
}