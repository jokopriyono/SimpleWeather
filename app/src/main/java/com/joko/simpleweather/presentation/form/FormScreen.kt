package com.joko.simpleweather.presentation.form

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joko.simpleweather.presentation.MainActivity
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
    viewModel: FormViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val uiState = viewModel.uiState
    val cityFocus by remember { mutableStateOf(FocusRequester()) }
    var isFirstTime by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.temperature) {
        if (uiState.temperature != null) {
            val c = uiState.temperature.celsius
            val f = uiState.temperature.fahrenheit
            val route = MainActivity.DETAIL_ROUTE
                .replace("{c}", c.toString())
                .replace("{f}", f.toString())
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
    LaunchedEffect(isFirstTime) {
        if (!isFirstTime) {
            viewModel.setState(uiState)
            isFirstTime = true
        }
    }

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
                .padding(top = 10.dp)
                .fillMaxWidth()
                .focusRequester(cityFocus),
            text = uiState.city,
            hint = "City Name",
            suggestions = uiState.filteredCities.map { it.name },
            onTextChange = {
                viewModel.setState(
                    uiState.copy(
                        city = it,
                        selectedCity = null
                    )
                )
            },
            onItemClick = {
                viewModel.setState(
                    uiState.copy(
                        city = uiState.filteredCities[it].name,
                        selectedCity = uiState.filteredCities[it],
                    )
                )
            },
            onActionClick = {
                if (uiState.formValid) viewModel.fetchWeather()
            },
            errorMessage = uiState.cityError,
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            onClick = {
                if (uiState.formValid) viewModel.fetchWeather()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            enabled = uiState.formValid
        ) {
            Text(text = "Submit", color = Color.White, fontWeight = FontWeight.Bold)
        }

        if (uiState.isLoading) JoLoading()
    }
}