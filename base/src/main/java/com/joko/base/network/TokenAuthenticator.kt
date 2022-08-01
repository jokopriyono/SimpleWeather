package com.joko.base.network

import com.joko.base.network.refreshtoken.RefreshTokenBody
import com.joko.base.network.refreshtoken.RefreshTokenResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.http.Body
import retrofit2.http.POST
import timber.log.Timber

class TokenAuthenticator(
    private val baseUrl: String,
    private val email: String?,
    private val oldToken: String?,
    private val onTokenUpdated: (String) -> Unit
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            var token = oldToken
            if (email != null && oldToken != null) {
                Timber.d("TIME TO REFRESH TOKEN")
                val body = RefreshTokenBody(email, oldToken)
                val apiAuth = createApi<TokenDataSource>(
                    createOkHttpClient(baseUrl, email, oldToken, onTokenUpdated), baseUrl
                )
                apiAuth.refreshToken(body).suspendOnSuccess {
                    token = data.data.token.token
                    onTokenUpdated(data.data.token.token)
                }
                response.request.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            } else null
        }
    }
}

private interface TokenDataSource {

    @POST("auth/refresh-token")
    suspend fun refreshToken(
        @Body body: RefreshTokenBody
    ): ApiResponse<RefreshTokenResponse>
}