package com.joko.simpleweather.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joko.base.activity.BaseActivity
import com.joko.simpleweather.presentation.detail.DetailScreen
import com.joko.simpleweather.presentation.form.FormScreen
import com.joko.simpleweather.presentation.form.FormViewModel
import com.joko.simpleweather.ui.theme.SimpleWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    companion object {
        const val FORM_ROUTE = "form"
        const val DETAIL_ROUTE = "detail/{c}/{f}"
    }

    private val formViewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SimpleWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold { pad ->
                        val navController = rememberNavController()

                        NavHost(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = pad.calculateBottomPadding()),
                            navController = navController,
                            startDestination = FORM_ROUTE,
                        ) {
                            composable(route = FORM_ROUTE) {
                                FormScreen(formViewModel, navToDetail = {
                                    val route = DETAIL_ROUTE
                                        .replace("{c}", it.celsius.toString())
                                        .replace("{f}", it.fahrenheit.toString())

                                    navController.navigate(route) {
                                        popUpTo(FORM_ROUTE)
                                        launchSingleTop = true
                                    }
                                })
                            }
                            composable(
                                route = DETAIL_ROUTE,
                                arguments = listOf(
                                    navArgument("c") { type = NavType.FloatType },
                                    navArgument("f") { type = NavType.FloatType },
                                )
                            ) {
                                val celsius = it.arguments?.getFloat("c")
                                val fahrenheit = it.arguments?.getFloat("f")
                                DetailScreen(celsius, fahrenheit)
                            }
                        }
                    }
                }
            }
        }

        setupObserver()
    }

    override fun setupObserver() {
        formViewModel.message.observe(this) {
            showMessage(it)
        }
        formViewModel.loadingStatus.observe(this) {
            formViewModel.setState(formViewModel.uiState.copy(isLoading = it))
        }
    }
}