package com.taxicar.views.dialogspopup.pinprocedures

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PinInputField(
    modifier: Modifier = Modifier,
    pinText: String,
    showDots: Boolean,
    pinCount: Int = 4,
    onPinTextChange: (Any?) -> Unit
) {
    val PINInputViewModel: com.taxicar.view.models.PINInputViewModel = viewModel()

    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(pinText, selection = TextRange(pinText.length)),
        onValueChange = {
            if (it.text.length <= pinCount) {
                onPinTextChange.invoke(it.text)

                if (it.text.length < pinCount) {
                    PINInputViewModel.wrongCheckPINInput = false
                    PINInputViewModel.wrongOldChangePIN = false
                    PINInputViewModel.wrongChangeRepeatPIN = false
                    PINInputViewModel.wrongCreateRepeatPIN = false
                    PINInputViewModel.isCheckPINTime = false
                }

                if (it.text.length == pinCount) {
                    PINInputViewModel.isCheckPINTime = true
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(pinCount) { index ->
                    PinView(
                        index = index,
                        text = pinText,
                        showDots = showDots
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
    )
}


@Composable
private fun PinView(
    index: Int,
    text: String,
    showDots: Boolean
) {
    val circleGradientBackground = Brush.horizontalGradient(
        colors = listOf(Color(0xFF856BFE), Color(0xFF5242A7))
    )
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }

    Text(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(brush = circleGradientBackground)
            .drawWithContent {
                if (char != "" && showDots) {
                    drawCircle(
                        color = Color(0xFF3E317E),
                        center = Offset(size.width / 2, size.height / 2),
                        radius = 0.6f * size.width / 2
                    )
                }
                drawContent()
            }
            .border(1.5.dp, Color(0xFF856BFE), CircleShape)
            .padding(top = 8.dp),

        text = char,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        color = if (!showDots) {
            Color(0xFF22DFCD)
        } else {
            Color.Transparent
        },
    )
}
