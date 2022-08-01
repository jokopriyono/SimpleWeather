package com.joko.simpleweather.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
private fun PreviewBVTTextField() {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)

    Column {
        JoTextField(modifier)
        JoTextField(modifier, errorMessage = "Error message here")
    }
}

@Composable
fun JoTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    imeAction: ImeAction = ImeAction.Default,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    errorMessage: String? = null,
    onChange: (String) -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.onFocusChanged { isFocused = it.isFocused },
            value = text,
            onValueChange = onChange,
            label = {
                Text(text = hint, color = if (isFocused) Color.Red else Color.Black)
            },
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(imeAction = imeAction),
            keyboardActions = KeyboardActions { onActionClick() },
            enabled = enabled,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            )
        )
        if (errorMessage != null) Text(text = errorMessage, color = Color.Red, fontSize = 12.sp)
    }
}
