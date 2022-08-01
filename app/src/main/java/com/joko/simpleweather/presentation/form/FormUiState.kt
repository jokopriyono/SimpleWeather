package com.joko.simpleweather.presentation.form

data class FormUiState(
    val apiKey: String = "",
    val apiKeyError: String? = null,
    val city: String = "",
    val cityError: String? = null,
    val cities: List<String> = listOf("Kuala Lumpur", "Singapore"),
    val selectedCity: String? = null,
    val isLoading: Boolean = false
)
