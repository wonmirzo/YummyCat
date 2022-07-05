package com.wonmirzo.network.services

import com.wonmirzo.helper.MyStrings
import com.wonmirzo.network.model.UploadItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface UploadService {
    @Headers("x-api-key:${MyStrings.accessKey}")

    @Multipart
    @POST("images/upload")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("file") file: RequestBody,
        @Part("sub_id") subId: String
    ): Call<UploadItem>
}