package com.joko.simpleweather.ui.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joko.simpleweather.ui.component.JoTextField

@Composable
@Preview(showBackground = true)
private fun PreviewDetailScreen() {
    DetailScreen()
}

@Composable
fun DetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
    ) {

        JoTextField()
        JoTextField()

    }
}