package com.joko.simpleweather.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
@Preview(showBackground = true)
fun JoLoading(
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = {}) {

        Card(shape = RoundedCornerShape(10.dp)) {
            Row(
                modifier = Modifier.padding(18.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Loading..")
            }
        }
    }
}