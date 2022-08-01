package com.joko.base.extensions

import org.json.JSONObject
import retrofit2.HttpException

fun Exception.getErrorException(): String? {
    return if (this is HttpException) {
        this.getErrorMessage()
    } else {
        this.message
    }
}

private fun Exception.getErrorMessage(): String {
    val httpException = this as HttpException
    val jsonObject = httpException.response()?.errorBody()?.charStream()?.readText()
    val errorBody = JSONObject(jsonObject.toString())
    return if (errorBody.has("error"))
        errorBody.getJSONObject("error").getString("message")
    else
        errorBody.getString("message")
}

fun Exception.getErrorExceptionSchedule(): String? {
    return if (this is HttpException) {
        this.getErrorMessageSchedule()
    } else {
        this.message
    }
}

private fun Exception.getErrorMessageSchedule(): String {
    val httpException = this as HttpException
    val jsonObject = httpException.response()?.errorBody()?.charStream()?.readText()
    val errorBody = JSONObject(jsonObject.toString())
    return when {
        errorBody.has("errors") -> errorBody.getString("errors")
        errorBody.has("error") -> errorBody.getJSONObject("error").getString("message")
        else -> errorBody.getString("message")
    }
}