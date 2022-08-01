package com.joko.base.network.refreshtoken

import com.google.gson.annotations.SerializedName

data class TokenData(
    @SerializedName("token")
    val token: Token
)