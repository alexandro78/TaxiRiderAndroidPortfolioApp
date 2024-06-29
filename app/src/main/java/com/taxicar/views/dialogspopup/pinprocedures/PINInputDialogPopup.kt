package com.taxicar.views.dialogspopup.pinprocedures

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.taxicar.R

@Composable
fun PINInputDialogPopup(
    PINInputViewModel: com.taxicar.view.models.PINInputViewModel,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    var checkInputPINValue by remember {
        mutableStateOf("")
    }

    var showDots by remember { mutableStateOf(true) }
    val confirmSuccess by remember { mutableStateOf("ОК") }

    if (PINInputViewModel.isCheckPINTime) {
        if (checkInputPINValue == "2222") {
            PINInputViewModel.checkPINInputSaveStatus = true
        } else {
            PINInputViewModel.wrongCheckPINInput = true
        }
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .size(250.dp),
            border = BorderStroke(0.5.dp, Color(0xFF856BFE)),
            shape = RoundedCornerShape(8.dp),
            colors = CardColors(
                containerColor = Color(0xFF101213),
                contentColor = Color(0xFFE1E4E9),
                disabledContainerColor = Color(0xFF101213),
                disabledContentColor = Color(0xFFE1E4E9)
            )
        ) {

            if (!PINInputViewModel.checkPINInputSaveStatus) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(modifier = Modifier) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {

                            IconButton(
                                onClick = { onDismissRequest() },
                                modifier = Modifier
                                    .offset(x = (4).dp),
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Localized description",
                                    tint = Color(0xFF8A8485),
                                    modifier = Modifier
                                        .size(28.dp),
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = if (PINInputViewModel.wrongCheckPINInput) {
                                    "PIN невірний!"
                                } else {
                                    "Введіть PIN"
                                },
                                color = if (PINInputViewModel.wrongCheckPINInput) {
                                    Color(0xFFEF5350)
                                } else {
                                    Color(0xFFD8D3D4)
                                },
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .padding(top = 30.dp)
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.6f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        PinInputField(
                            modifier = Modifier
                                .width(180.dp)
                                .height(35.dp),
                            showDots = showDots,
                            pinText = checkInputPINValue,
                            onPinTextChange = { value ->
                                checkInputPINValue = value.toString()
                            }
                        )

                        Spacer(modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .offset(y = 30.dp)
                            .drawBehind {
                                drawLine(
                                    color = Color(0xFF856BFE),
                                    start = Offset(0f, size.height / 2),
                                    end = Offset(size.width, size.height / 2),
                                    strokeWidth = 1.dp.toPx(),
                                    cap = StrokeCap.Round,
                                    pathEffect = PathEffect.dashPathEffect(
                                        intervals = floatArrayOf(
                                            0f, 6.dp.toPx(),
                                        ),
                                    ),
                                )
                            }
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 4.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(modifier = Modifier,
                            onClick = { showDots = !showDots }) {
                            Icon(
                                imageVector = if (showDots) {
                                    ImageVector.vectorResource(id = R.drawable.visibility_off_icon)
                                } else {
                                    ImageVector.vectorResource(id = R.drawable.visibility_on_icon)
                                },
                                contentDescription = "Localized description",
                                tint = Color(0xFFB9B4B5),
                                modifier = Modifier
                                    .size(26.dp),
                            )
                        }
                    }

                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally)
                ) {
                    IconButton(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                        onClick = { onConfirmation() }) {

                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.lock_icon2),
                            contentDescription = "Localized description",
                            tint = Color(0xFF856BFE),
                            modifier = Modifier
                                .size(50.dp),
                        )

                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.done_icon),
                            contentDescription = "Localized description",
                            tint = Color(0xFF3CE9BE),
                            modifier = Modifier
                                .size(30.dp)
                                .offset(x = 6.dp, y = 10.dp),
                        )
                    }

                    ClickableText(
                        text = AnnotatedString(confirmSuccess),
                        onClick = { onConfirmation() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .offset(y = 40.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFF3CE9BE),
                            textAlign = TextAlign.Center
                        )
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        onConfirmation()
                    }, 1500)
                }
            }
        }
    }
}