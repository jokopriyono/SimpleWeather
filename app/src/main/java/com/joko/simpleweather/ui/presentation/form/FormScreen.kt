package com.joko.simpleweather.ui.presentation.form

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joko.simpleweather.ui.component.JoDropDown
import com.joko.simpleweather.ui.component.JoLoading
import com.joko.simpleweather.ui.component.JoTextField

@Composable
@Preview(showBackground = true)
private fun PreviewFormScreen() {
    FormScreen()
}

@Composable
fun FormScreen(
    viewModel: FormViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val cityFocus by remember { mutableStateOf(FocusRequester()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        JoTextField(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.apiKey,
            hint = "Your API Key",
            onChange = { viewModel.setState(uiState.copy(apiKey = it)) },
            errorMessage = uiState.apiKeyError,
            imeAction = ImeAction.Next,
            onActionClick = { cityFocus.requestFocus() }
        )
        JoDropDown(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .focusRequester(cityFocus),
            text = uiState.city,
            hint = "City Name",
            suggestions = uiState.cities,
            onTextChange = { viewModel.setState(uiState.copy(city = it)) },
            onActionClick = { viewModel.fetchWeather() },
            errorMessage = uiState.cityError,
        )

        if (uiState.isLoading) JoLoading()
    }
}