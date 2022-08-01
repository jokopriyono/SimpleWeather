package com.joko.base.repository

import android.os.Parcelable
import com.joko.base.BuildConfig
import com.joko.base.extensions.fromJson
import com.joko.base.network.BaseRequest
import com.joko.base.network.BaseResponse
import com.joko.base.network.Resource
import com.google.gson.Gson
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.parcelize.Parcelize
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseRepository(
    private val ioDispatcher: CoroutineDispatcher
) {
    protected suspend fun <T : Any> fetchRemote(remoteApi: ApiResponse<T>): Flow<Resource<T>> {
        return flow {
            emit(Resource.Loading)
            remoteApi
                .suspendOnSuccess {
                    emit(Resource.Success(data))
                }
                .suspendOnError {
                    var catch = false
                    runCatching {
                        errorBody?.string()?.let { str ->
                            Gson().fromJson<DefaultResponse?>(str)?.let { errorBody ->
                                emit(Resource.Exception(Exception(errorBody.message)))
                                catch = true
                            }
                        }
                    }
                    if (!catch) emit(
                        Resource.Exception(
                            Exception(
                                if (BuildConfig.DEBUG) this.raw.message
                                else "Terjadi kesalahan, kode: 1001"
                            )
                        )
                    )
                }
                .suspendOnException {
                    emit(
                        Resource.Exception(
                            Exception(
                                when (exception) {
                                    is UnknownHostException, is IOException -> "Cek koneksi internet anda"
                                    is SocketTimeoutException -> "Tidak dapat terhubung ke internet, silahkan coba kembali"
                                    else -> if (BuildConfig.DEBUG) exception.message else "Terjadi kesalahan, kode: 1002"
                                }
                            )
                        )
                    )
                }
        }.flowOn(ioDispatcher)
    }

    @Parcelize
    private data class DefaultResponse(
        override val message: String,
        override val request: BaseRequest,
        override val statusCode: Int,
        override val data: String?
    ) : BaseResponse<String?>(), Parcelable
}