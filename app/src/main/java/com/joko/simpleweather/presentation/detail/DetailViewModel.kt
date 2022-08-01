package com.joko.simpleweather.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.joko.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : BaseViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set

    fun setState(uiState: DetailUiState) {

    }

}