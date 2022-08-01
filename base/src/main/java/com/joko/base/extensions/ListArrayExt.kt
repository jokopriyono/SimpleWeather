package com.joko.base.extensions

fun <T> getFirst(list: List<T>?): T? {
    return if (list != null && list.isNotEmpty()) list[0] else null
}

fun <T> getLast(list: List<T>?): T? {
    return if (list != null && list.isNotEmpty()) list[list.size - 1] else null
}