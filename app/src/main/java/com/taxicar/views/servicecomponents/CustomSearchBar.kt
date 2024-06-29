package com.taxicar.views.servicecomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    customCursorStyle: Brush = SolidColor(Color.Black),
    hideTextField: () -> Unit,
    placeholderTextColor: Color = Color.White,
    value: String,
    onValueChanged: (String) -> Unit,
    placeholder: String = "",
    textStyle: TextStyle = TextStyle.Default,
) {

    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(modifier = modifier) {
                if (value.isEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = placeholder,
                            fontSize = 14.sp,
                            color = placeholderTextColor
                        )
                    }
                }
                innerTextField()
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                hideTextField()
            }
        ),
        cursorBrush = customCursorStyle
    )
}