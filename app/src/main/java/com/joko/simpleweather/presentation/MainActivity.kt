package com.joko.simpleweather.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.joko.base.activity.BaseActivity
import com.joko.simpleweather.presentation.form.FormScreen
import com.joko.simpleweather.presentation.form.FormViewModel
import com.joko.simpleweather.ui.theme.SimpleWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SimpleWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    FormScreen()
                }
            }
        }

        setupObserver()
    }

    override fun setupObserver() {
        viewModel.message.observe(this) {
            showMessage(it)
        }
        viewModel.loadingStatus.observe(this) {
            viewModel.setState(viewModel.uiState.copy(isLoading = it))
        }
    }
}