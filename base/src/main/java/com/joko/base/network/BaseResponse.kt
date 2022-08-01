package com.joko.base.network


import android.os.Parcelable
import com.google.gson.annotations.SerializedName

abstract class BaseResponse<T> : Parcelable {
    @get:SerializedName("message")
    abstract val message: String

    @get:SerializedName("request")
    abstract val request: BaseRequest

    @get:SerializedName("status_code")
    abstract val statusCode: Int

    @get:SerializedName("data")
    abstract val `data`: T?
}