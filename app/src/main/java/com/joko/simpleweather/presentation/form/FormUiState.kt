package com.joko.simpleweather.presentation.form

import com.joko.domain.entity.TemperatureEntity

data class FormUiState(
    val apiKey: String = "",
    val apiKeyError: String? = null,
    val city: String = City.KualaLumpur().name,
    val cityError: String? = null,
    val cities: List<City> = listOf(City.KualaLumpur(), City.Singapore()),
    val filteredCities: List<City> = cities,
    val selectedCity: City? = City.KualaLumpur(),

    val temperature: TemperatureEntity? = null,

    val isLoading: Boolean = false,
    val formValid: Boolean = false
) {
    sealed class City(val name: String) {
        class KualaLumpur: City("Kuala Lumpur")
        class Singapore: City("Singapore")
    }
}
