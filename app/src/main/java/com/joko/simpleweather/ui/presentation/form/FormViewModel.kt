package com.joko.simpleweather.ui.presentation.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.joko.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(

) : BaseViewModel() {

    var uiState by mutableStateOf(FormUiState())
        private set

    fun setState(uiState: FormUiState) {
        viewModelScope.launch {
            val apiKeyError = if (uiState.apiKey.isEmpty()) "Api key required" else null
            val cityError = if (uiState.city.isEmpty()) "Api key required" else null
            val newState = uiState.copy(
                apiKeyError = apiKeyError,
                cityError = cityError
            )

            this@FormViewModel.uiState = newState
        }
    }

    fun fetchWeather() {
        viewModelScope.launch {

        }
    }
}