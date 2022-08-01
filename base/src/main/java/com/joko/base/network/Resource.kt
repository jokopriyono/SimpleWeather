package com.joko.base.network

sealed class Resource<out T : Any?> {

    class Success<out T : Any>(val value: T, val message: String = "") : Resource<T>() {
        override fun toString() = "Result.Ok{value=$value, response=$message}"
    }

    class Exception(val exception: kotlin.Exception) : Resource<Nothing>()
//    class Exception<out T : Any>(val message: String = "") : Resource<T>() {
//        override fun toString() = "$message"
//    }
    object Loading : Resource<Nothing>()

}

