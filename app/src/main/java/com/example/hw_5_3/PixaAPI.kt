package com.example.hw_5_3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaAPI {
    @GET("api/")
    fun getImages(
        @Query("key")
        key: String = "43178747-b195adf1942cd03718caa837c",
        @Query("q")
        searchWord: String,
        @Query("page")
        page: Int,
        @Query("per_page")
        perPage: Int = 3
    ): Call<PixaModel>
}