package com.joko.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TemperatureEntity(
    val celsius: Double,
    val fahrenheit: Double
) : Parcelable
