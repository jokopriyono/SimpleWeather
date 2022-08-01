package com.joko.base.extensions

fun Float.toRatingDesc(textDefault: String?): String {
    return when(this) {
        in 4.1..5.0 -> "Luar Biasa"
        in 3.1..4.0 -> "Bagus"
        in 2.1..3.0 -> "Biasa"
        in 1.1..2.0 -> "Butuh Perbaikan"
        in 0.1..1.0 -> "Sangat Buruk"
        else -> textDefault ?: ""
    }
}