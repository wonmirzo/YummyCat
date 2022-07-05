package com.wonmirzo.network.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class UploadItem(
    var file: Int,
    @SerializedName("sub_id")
    var subId: String
)
