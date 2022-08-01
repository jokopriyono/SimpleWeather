package com.joko.base.network.refreshtoken

import com.google.gson.annotations.SerializedName

data class RefreshTokenBody(
    @SerializedName("email_username")
    val emailUsername: String,
    val refreshToken: String,
)
