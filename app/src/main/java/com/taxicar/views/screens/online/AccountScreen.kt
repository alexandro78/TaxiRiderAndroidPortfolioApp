package com.taxicar.views.screens.online

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taxicar.R
import com.taxicar.views.dialogspopup.pinprocedures.PINInputDialogPopup
import com.taxicar.views.scaffoldcontainer.AdvancedMainScaffoldContainer


@Composable
fun PersonalChatAccountScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("last seen 15m ago")
        }
    }

    AdvancedMainScaffoldContainer(
        centerHeaderText = headerText,
        navIcon = { /*PersonalChatAccountScreenHeadIconButton()*/ },
        bodyContent = { PersonalChatAccountScreenContentSection() })
}


@Composable
fun PersonalChatAccountScreenHeadIconButton() {
//    val navController = LocalNavController.current
    IconButton(onClick = { /*navController.popBackStack()*/ }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Localized description",
            tint = Color(0xFF616161),
            modifier = Modifier
                .size(20.dp)
        )
    }
}


@Composable
fun LeftNickNameHeadePart() {
    var nickName by remember { mutableStateOf("Nickname") }

    ClickableText(
        text = AnnotatedString(nickName),
        onClick = {},
        modifier = Modifier,
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 16.sp,
            color = Color(0xFFECECEE),
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
        )
    )
}


@Composable
fun PersonalChatAccountScreenContentSection() {
    var viewDetails by remember { mutableStateOf<TextLayoutResult?>(null) }

    val timerStatus = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 18.sp)) {
            append("Таймер зустрічі активований!")
        }
    }

    val timerRemainingTime = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFE15241), fontSize = 16.sp)) {
            append("Залишилося: 47 год : 54 хв")
        }
    }

    val avatarBackgroundGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFB1B0B6), Color(0xFF616161))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {

        Spacer(modifier = Modifier.height(18.dp))

        //// Timer Box //////////
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row {
                    Text(text = timerStatus)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row {
                    Text(
                        text = timerRemainingTime,
                        fontSize = 16.sp
                    )
                }

                Row {
                    Text(
                        text = "Деталі",
                        color = Color(0xFFF07C58),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        onTextLayout = {
                            viewDetails = it
                        },
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .drawBehind {

                                viewDetails?.let {
                                    val thickness = 3f
                                    val dashPath = 5f
                                    val spacingExtra = 2f
                                    val offsetY = 4f

                                    for (i in 0 until it.lineCount) {
                                        drawPath(
                                            path = androidx.compose.ui.graphics
                                                .Path()
                                                .apply {
                                                    moveTo(
                                                        it.getLineLeft(i),
                                                        it.getLineBottom(i) - spacingExtra + offsetY
                                                    )
                                                    lineTo(
                                                        it.getLineRight(i),
                                                        it.getLineBottom(i) - spacingExtra + offsetY
                                                    )
                                                },
                                            Color(0xFFF07C58),
                                            style = Stroke(
                                                width = thickness,
                                                pathEffect = PathEffect.dashPathEffect(
                                                    floatArrayOf(
                                                        dashPath,
                                                        dashPath
                                                    ), 0f
                                                )
                                            )
                                        )
                                    }
                                }
                            }
                    )
                }
            }
        }
        //////// end timer Box ////////

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar_image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(0.5.dp, Color(0xFF3CE9BE), shape = CircleShape)
                    .background(avatarBackgroundGradient)
            )
        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            SecretChatInfoAttention()
        }

        Spacer(modifier = Modifier.height(10.dp))

        SecretChatRowToggle()

        Spacer(modifier = Modifier.height(10.dp))

        BlackListRowToggle()

        Spacer(modifier = Modifier.height(30.dp))

        MeetingEndCode()

        Spacer(modifier = Modifier.height(20.dp))

        EndMeetingCodeFieldInput()

        Spacer(modifier = Modifier.height(10.dp))

        FooterInfoIcons()
    }
}


@Composable
fun SecretChatInfoAttention() {
    val myId = "inlineContent"
    val myId2 = "inlineContent2"

    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 14.sp)) {
            append("Таємні чати не відображаються в загальному списку контактів. Щоб відкрити список таємних чатів які ви сховали за допомогою функції ")
            appendInlineContent(myId, "[icon]")
            append(" натисніть іконку пошуку ")
            appendInlineContent(myId2, "[icon2]")
            append(" в загальному списку та введіть чотиризначний PIN в поле пошуку! З'являться ті контакти які ви сховали як таємні.")
        }
    }

    val inlineContent = mapOf(
        Pair(
            myId,
            InlineTextContent(
                Placeholder(
                    width = 22.sp,
                    height = 22.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.unlocked_padlock),
                    contentDescription = "Localized description",
                    tint = Color(0xFF3CE9BE),
                    modifier = Modifier
                )
            }
        ),
        Pair(
            myId2,
            InlineTextContent(
                Placeholder(
                    width = 22.sp,
                    height = 22.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Localized description",
                    tint = Color(0xFF856BFE),
                    modifier = Modifier
                )
            }
        )
    )

    Text(
        text = text,
        modifier = Modifier,
        inlineContent = inlineContent
    )
}


@Composable
fun SecretChatRowToggle() {
    var isCheckInputPINDialogVisible by remember { mutableStateOf(false) }
    val PINInputViewModel: com.taxicar.view.models.PINInputViewModel = viewModel()
    var secretChatToggle by remember { mutableStateOf(false) } //замінити на запит статусу з бази
    val secretChatActive = "Чат зроблено таємним"
    val secretChatInactive = "Зробити цей чат таємним та сховати його"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .background(
                Color(0xFF302E2E),
                shape = RoundedCornerShape(8.dp),
            ),
        verticalAlignment = Alignment.CenterVertically

    ) {
        IconButton(onClick = {
//            secretChatToggle = !secretChatToggle
            isCheckInputPINDialogVisible = true
        }) {
            Icon(
                imageVector = if (secretChatToggle) {
                    ImageVector.vectorResource(id = R.drawable.lock_padlock)
                } else {
                    ImageVector.vectorResource(id = R.drawable.unlocked_padlock)
                },
                contentDescription = "Localized description",
                tint = Color(0xFF3CE9BE),
                modifier = Modifier.size(24.dp)
            )

        }

        ClickableText(
            text = if (secretChatToggle) {
                AnnotatedString(secretChatActive)
            } else {
                AnnotatedString(secretChatInactive)
            },
            onClick = {
//                secretChatToggle = !secretChatToggle
                isCheckInputPINDialogVisible = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp, bottom = 18.dp),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                color = Color(0xFFB6B0B0)
            )
        )
    }

    if (isCheckInputPINDialogVisible) {
        PINInputDialogPopup(
            PINInputViewModel = PINInputViewModel.apply {
                isCheckPINTime = false
            },
            onDismissRequest = {
                // Here you can add the logic for closing the dialog box
                isCheckInputPINDialogVisible = false
            },
            onConfirmation = {
                // Here you can add the logic for confirmation the dialog
                secretChatToggle = !secretChatToggle
                isCheckInputPINDialogVisible = false
                PINInputViewModel.checkPINInputSaveStatus = false
            }
        )
    }
}


@Composable
fun BlackListRowToggle() {
    var blackListToggle by remember { mutableStateOf(false) }
    val addedToBlackList = "Цей аккаунт в чорному списку"
    val addToBlackList = "Додати аккаунт в чорний список"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .background(
                Color(0xFF302E2E),
                shape = RoundedCornerShape(8.dp),
            ),
        verticalAlignment = Alignment.CenterVertically

    ) {
        IconButton(onClick = { blackListToggle = !blackListToggle }) {
            Icon(
                imageVector = if (blackListToggle) {
                    ImageVector.vectorResource(id = R.drawable.person_add_icon)
                } else {
                    ImageVector.vectorResource(id = R.drawable.person_remove_icon)
                },
                contentDescription = "Localized description",
                tint = if (blackListToggle) {
                    Color(0xFF3CE9BE)
                } else {
                    Color(0xFFE91C1C)
                },
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(26.dp)
            )

        }

        ClickableText(
            text = if (blackListToggle) {
                AnnotatedString(addedToBlackList)
            } else {
                AnnotatedString(addToBlackList)
            },
            onClick = {
                blackListToggle = !blackListToggle
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp, bottom = 18.dp),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                color = if (blackListToggle) {
                    Color(0xFFE91C1C)
                } else {
                    Color(0xFFB6B0B0)
                },
            )
        )
    }
}


@Composable
fun MeetingEndCode() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Код завершення зустрічі з'явиться нижче",
                color = Color(0xFFCFC6C6),
                fontSize = 16.sp,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "_ _ _ _",
                color = Color(0xFF3CE9BE),
                fontSize = 16.sp,
                modifier = Modifier
                    .background(Color(0xFF3F4140), shape = RoundedCornerShape(4.dp))
                    .padding(start = 8.dp, end = 8.dp, top = 6.dp, bottom = 3.dp)
            )
        }
    }
}


@Composable
fun EndMeetingCodeFieldInput() {
    var endMeetingStatusToggle by remember { mutableStateOf(false) }
    val endMeetingStatusFalse = "Ввести код завершення зустрічі"
    val endMeetingStatusTrue = "Код завершення зустрічі введено"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Icon(
                imageVector = if (endMeetingStatusToggle) {
                    ImageVector.vectorResource(id = R.drawable.alarm_on_icon)
                } else {
                    ImageVector.vectorResource(id = R.drawable.alarm_clock_icon)
                },
                contentDescription = "Localized description",
                tint = if (endMeetingStatusToggle) {
                    Color(0xFF3CE9BE)
                } else {
                    Color(0xFFB6B0B0)
                },
                modifier = Modifier
                    .padding(start = 4.dp, top = 6.dp, end = 8.dp)
                    .size(26.dp)
            )


            if (endMeetingStatusToggle) {
                Text(
                    text = endMeetingStatusTrue,
                    color = Color(0xFF3CE9BE),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = 2.dp, bottom = 2.dp)
                )
            } else {
                Text(
                    text = endMeetingStatusFalse,
                    color = Color(0xFFB6B0B0),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = 2.dp, bottom = 2.dp)
                )
            }
            InputCodeTextField()
        }
    }
}


@Composable
fun InputCodeTextField() {
    var endMeetingStatusCode by remember {
        mutableStateOf("")
    }

    PinInputField(
        modifier = Modifier
            .width(200.dp)
            .offset(x = (-10).dp),

        pinText = endMeetingStatusCode,
        onPinTextChange = { value ->
            endMeetingStatusCode = value.toString()
        }
    )
}


@Composable
fun PinInputField(
    modifier: Modifier = Modifier,
    pinText: String,
    pinCount: Int = 4,
    onPinTextChange: (Any?) -> Unit
) {

    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(pinText, selection = TextRange(pinText.length)),
        onValueChange = {
            if (it.text.length <= pinCount) {
                onPinTextChange.invoke(it.text)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(pinCount) { index ->
                    PinView(
                        index = index,
                        text = pinText
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }
        }
    )
}


@Composable
private fun PinView(
    index: Int,
    text: String,
) {
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }

    Text(
        modifier = Modifier
            .size(25.dp, 30.dp)
            .border(1.dp, Color.Blue, RoundedCornerShape(6.dp))
            .padding(top = 5.dp),

        text = char,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        color = Color(0xFF3CE9BE),
    )
}


@Composable
fun FooterInfoIcons() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.chat_icon),
                contentDescription = "Localized description",
                tint = Color(0xFFBA00FF),
                modifier = Modifier
                    .size(22.dp)
            )
        }

        IconButton(onClick = { }) {
            Box(
                modifier = Modifier
                    .width(70.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.local_bar_icon),
                    contentDescription = "Localized description",
                    tint = Color(0xFF856BFE),
                    modifier = Modifier
                        .size(20.dp)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.local_cafe_icon),
                    contentDescription = "Localized description",
                    tint = Color(0xFF5FBE9F),
                    modifier = Modifier
                        .offset(x = 10.dp, y = 2.dp)
                        .size(22.dp)
                )
            }
        }

        IconButton(onClick = { }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.alarm_off),
                contentDescription = "Localized description",
                tint = Color(0xFFCE0707),
                modifier = Modifier
                    .size(23.dp)
            )
        }
    }
}


