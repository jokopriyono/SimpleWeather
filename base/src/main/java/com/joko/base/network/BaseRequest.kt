package com.joko.base.network


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseRequest(
    @SerializedName("message")
    val message: String,

    @SerializedName("status_code")
    val statusCode: Int,

    @SerializedName("url")
    val url: String
) : Parcelable