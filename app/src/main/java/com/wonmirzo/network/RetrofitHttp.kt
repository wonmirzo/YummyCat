package com.wonmirzo.network

import com.wonmirzo.network.services.SearchService
import com.wonmirzo.network.services.UploadService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    const val IS_TESTER = true
    private const val SERVER_DEVELOPMENT = "https://api.thecatapi.com/v1/"
    private const val SERVER_PRODUCTION = "https://api.thecatapi.com/v1/"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val searchService: SearchService = retrofit.create(SearchService::class.java)
    val uploadService: UploadService = retrofit.create(UploadService::class.java)
}