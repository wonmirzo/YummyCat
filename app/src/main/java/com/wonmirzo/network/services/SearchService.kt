package com.wonmirzo.network.services

import com.wonmirzo.helper.MyStrings
import com.wonmirzo.network.model.SearchItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface SearchService {
    @Headers("x-api-key:${MyStrings.accessKey}")

    @GET("images/search")
    fun listPost(@QueryMap images: MutableMap<String, String>): Call<List<SearchItem>>
}