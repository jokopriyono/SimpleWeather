package com.joko.base.extensions

import java.text.DateFormatSymbols
import java.text.NumberFormat
import java.util.*

fun Int.rupiah(): String {
    return try {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.format(this).toString()
    } catch (e: Exception) {
        "-"
    }
}

fun Int.convertToMonthName(): String {
    return DateFormatSymbols().months[this]
}


fun Int.convertIndexToFormatUpload() = if (this == 0) "P" else "L"

fun Int.convertIndexCitizenToFormatUpload() = if (this == 0) "WNI" else "WNA"

fun Int.convertMonthToFormatPostNewData() : String {
    return if (this + 1 < 10) "0" + (this + 1) else (this + 1).toString()
}

