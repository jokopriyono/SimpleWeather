package com.joko.simpleweather.presentation.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.joko.base.viewmodel.BaseViewModel
import com.joko.domain.entity.TemperatureEntity
import com.joko.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FormViewModel @Inject constructor(
    @Named("apiKey") private val apiKey: String,
    private val weatherUseCase: WeatherUseCase
) : BaseViewModel() {

    var uiState by mutableStateOf(FormUiState(apiKey = apiKey))
        private set

    fun setState(uiState: FormUiState) {
        viewModelScope.launch {
            val apiKeyError = if (uiState.apiKey.isEmpty()) "Api key required" else null
            val cityError = when {
                uiState.city.isEmpty() -> "City required"
                uiState.selectedCity == null -> "Please select one city"
                else -> null
            }
            val filteredCities =
                if (uiState.selectedCity == null) uiState.cities.filter {
                    it.name.contains(
                        uiState.city,
                        true
                    )
                } else listOf()
            val formValid = apiKeyError == null && uiState.apiKey.isNotEmpty()
                    && cityError == null && uiState.city.isNotEmpty()
                    && uiState.selectedCity != null

            val newState = uiState.copy(
                apiKeyError = apiKeyError,
                cityError = cityError,
                filteredCities = filteredCities,
                formValid = formValid,
            )

            this@FormViewModel.uiState = newState
        }
    }

    fun fetchWeather(onSuccess: (TemperatureEntity) -> Unit) {
        viewModelScope.launch {
            weatherUseCase.getCurrentWeather(uiState.selectedCity?.name ?: "").collectResult(
                onSuccess = onSuccess,
                onError = {
                }
            )
        }
    }
}