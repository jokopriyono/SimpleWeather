package com.joko.base.extensions

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import java.io.File
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.formatToDateTimeDefaults(): String? {
    val convert = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatToDateTimeWithHours(): String? {
    val convert = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatToDateTimeDetailStore(): String? {
    val convert = SimpleDateFormat("MMM dd , yyyy", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatToDateTimeDay(): String? {
    val convert = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatToDateTimeProfile(): String? {
    val convert = SimpleDateFormat("MMM , dd , yyyy", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatDateToMont(): String? {
    val convert = SimpleDateFormat("MM", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatDateToMonthName(): String? {
    val convert = SimpleDateFormat("MMM", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatDateToDay(): String? {
    val convert = SimpleDateFormat("dd", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatDateToYears(): String? {
    val convert = SimpleDateFormat("yyyy", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatDateToMontYear(): String? {
    val convert = SimpleDateFormat("MMM yyyy", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatToDateTimeStoreList(): String? {
    val convert = SimpleDateFormat("dd MMM", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatToDateTimeStoreStatusList(): String? {
    val convert = SimpleDateFormat("MMM dd", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatToTime(): String? {
    val convert = SimpleDateFormat("HH:mm", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatToTimeAmerican(): String? {
    val convert = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'KK:mm:ss", Locale.ENGLISH)
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}

fun String.formatToDateToNormalDate(): String? {
    val convert = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parse = sdf.parse(this)
    return parse?.let { convert.format(it) }
}


fun String.convertGender() = if (this.equals("l", true)) "Laki Laki" else "Perempuan"

fun String.convertSelectedRadioButton(optionsText: List<String>): Int {
    optionsText.forEachIndexed { index, text ->
        if (this.convertGender().contentEquals(text)) {
            return index
        }
    }
    return 0
}

fun String.isEmail(): Boolean {
    val p =
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])".toRegex()
    return matches(p)
}

fun String?.defaultText(): String? {
    val item = this
    return if (item.isNullOrEmpty()) {
        "-"
    } else {
        this
    }
}

fun String.toRequestTextBody(): RequestBody {
    return toRequestBody("text/plain".toMediaType())
}

fun File.toRequestBody(): RequestBody {
    return asRequestBody("multipart/form-data".toMediaTypeOrNull())
}

fun File?.toNullableRequestBody(): RequestBody? {
    return this?.asRequestBody("multipart/form-data".toMediaTypeOrNull())
}

fun String.toRupiah(): String {
    return try {
        val formatter = DecimalFormat("Rp #,###")
        val value = this.toDouble()
        formatter.format(value).replace(",", ".")
    } catch (e: Exception) {
        Timber.e(e)
        "Rp. 0"
    }
}

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { word ->
        word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

fun String.getInitial(): String {
    return this.split(" ").reduce { acc, name ->
        if (!name.lowercase(Locale.getDefault()).startsWith("pt")) {
            if (name.isNotEmpty()) acc + name.first()
        }
        acc
    }.uppercase(Locale.getDefault())
}

fun String.decodeJWTPayload(): String {
    val split = this.split(".")
    val decodedBytes: ByteArray = android.util.Base64.decode(split[1], android.util.Base64.URL_SAFE)
    return String(decodedBytes, Charsets.UTF_8)
}

fun String.toFlagEmoji(): String {
    if (this.length != 2) return this
    val countryCodeCaps = this.uppercase()
    val firstLetter = Character.codePointAt(countryCodeCaps, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCodeCaps, 1) - 0x41 + 0x1F1E6
    if (!countryCodeCaps[0].isLetter() || !countryCodeCaps[1].isLetter()) return this
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}