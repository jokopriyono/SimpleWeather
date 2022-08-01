package com.joko.base.network.refreshtoken

data class RefreshTokenResponse(
    val message: String,
    val statusCode: Int,
    val data: TokenData
)