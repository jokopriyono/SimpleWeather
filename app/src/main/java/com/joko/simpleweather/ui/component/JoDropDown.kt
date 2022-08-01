package com.joko.simpleweather.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties

@Composable
@Preview(showBackground = true)
private fun PreviewJoDropDown() {
    JoDropDown()
}

@Composable
@Preview(showBackground = true)
fun JoDropDown(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    enabled: Boolean = true,
    errorMessage: String? = null,
    suggestions: List<String> = listOf(),
    onTextChange: (String) -> Unit = {},
    onItemClick: (Int) -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    val localDensity = LocalDensity.current
    var menuWidthSize by remember { mutableStateOf(0.dp) }
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier
            .onGloballyPositioned {
                with(localDensity) { menuWidthSize = it.size.width.toDp() }
            }
    ) {
        JoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused },
            text = text,
            hint = hint,
            imeAction = ImeAction.Done,
            enabled = enabled,
            onChange = onTextChange,
            onActionClick = onActionClick,
            errorMessage = errorMessage,
        )
        if (suggestions.isNotEmpty() && isFocused) {
            DropdownMenu(
                modifier = Modifier
                    .width(menuWidthSize)
                    .requiredSizeIn(maxHeight = 200.dp)
                    .shadow(0.dp),
                expanded = true,
                onDismissRequest = {},
                properties = PopupProperties(clippingEnabled = false),
            ) {
                suggestions.forEachIndexed { index, item ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick(index) }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        text = item
                    )
                }
            }
        }
    }
}
