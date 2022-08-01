package com.joko.simpleweather.ui.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.joko.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : BaseViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set

    fun setState(uiState: DetailUiState) {

    }

}