package com.joko.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joko.base.SingleLiveEvent
import com.joko.base.exception.SessionExpiredException
import com.joko.base.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _message = SingleLiveEvent<Message>()
    val message: LiveData<Message> = _message

    private val _loadingStatus = MutableLiveData<Boolean>()
    val loadingStatus: LiveData<Boolean> = _loadingStatus

    val userSession = MutableLiveData<Boolean>()

    protected suspend fun <T> Flow<Resource<T>>.collectResult(
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
    ) {
        this.onStart {
            showLoading()
        }.onCompletion {
            hideLoading()
        }.collect {
            when (it) {
                is Resource.Success -> {
                    if (it.value == null) onError.invoke(Exception("Failed fetching, code: 4041"))
                    else onSuccess.invoke(it.value)
                }
                is Resource.Exception -> {
                    if (it.exception is SessionExpiredException) {
                        viewModelScope.launch {
                            userSession.postValue(true)
                        }
                    } else {
                        onError.invoke(it.exception)
                    }
                }
                is Resource.Loading -> {}
            }
        }
    }

    private fun showLoading() {
        _loadingStatus.value = true
    }

    private fun hideLoading() {
        if (_loadingStatus.value == true) {
            _loadingStatus.value = false
        }
    }

}