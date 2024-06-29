package com.taxicar.views.screens.online

import android.util.Log
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.ScaffoldContainerWithBottomBar
import com.taxicar.views.servicecomponents.CustomSearchBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun ChatFeedSwipeContainerToRevealUnderneath() {
    val squareSize = 150.dp
    val swipeAbleState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, -sizePx * 2.56f to 1)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFEE58))
            .swipeable(
                state = swipeAbleState,
                anchors = anchors,
                thresholds = { _, _ ->
                    FractionalThreshold(0.5f)
                },
                orientation = Orientation.Horizontal
            )
    ) {
        ///Lower screen content
        PersonalChatAccountScreen()
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(swipeAbleState.offset.value.roundToInt(), 0)
                }
                .fillMaxWidth()
                .background(Color(0xFFEC407A))
        ) {
            ///Top screen
            ChatFeedScreen()
        }
    }
}


@Composable
fun ChatFeedScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("last seen 45m ago")
        }
    }

    val headerTextOff = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("")
        }
    }

    val feedScreenViewModel: com.taxicar.view.models.FeedScreenViewModel = viewModel()

    ScaffoldContainerWithBottomBar(
        headerText = if (feedScreenViewModel.isSerchHeadIconButtonClick) {
            headerText
        } else {
            headerTextOff
        },
        navIcon = {
            if (feedScreenViewModel.isSerchHeadIconButtonClick) {
                ChatFeedScreenHeadIconButton()
            }
        },
        action = {
            ChatFeedHeadActionIconButton()
        },
        bottomBar = {
            MessageInputField()
        },
        bodyContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF000000))
            ) {
                ChatFeedContentSection()
                Text(
                    "Зробити будь-який чат таємним, та сховати його зі списку контактів, свайп ліворуч, до налаштувань! ",
                    color = Color(0xFF848589),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 12.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .offset(y = 3.dp)

                )
            }

        }
    )
}


@Composable
fun ChatFeedScreenHeadIconButton() {
    val navController = LocalNavController.current
    IconButton(onClick = {
        navController.popBackStack()
    }) {
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
fun ChatFeedHeadActionIconButton() {
    val feedScreenViewModel: com.taxicar.view.models.FeedScreenViewModel = viewModel()

    if (feedScreenViewModel.isSerchHeadIconButtonClick) {

        ///Сюди потрібно ім'я аканту....


        IconButton(onClick = {
            feedScreenViewModel.isSerchHeadIconButtonClick = false
        }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Localized description",
                tint = Color(0xFF856BFE),
                modifier = Modifier
                    .size(26.dp)
            )
        }
    } else {
        SearchChatFeedField()
    }
}


@Composable
fun SearchChatFeedField() {
    val feedScreenViewModel: com.taxicar.view.models.FeedScreenViewModel = viewModel()
    var searchFieldValue by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
    ) {

        IconButton(onClick = {
            feedScreenViewModel.isSerchHeadIconButtonClick = true
        }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Localized description",
                tint = Color(0xFF856BFE),
                modifier = Modifier
                    .size(20.dp)
                    .offset(y = (-7).dp)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        CustomSearchBar(
            customCursorStyle = SolidColor(Color.Green),
            hideTextField = {
//                    personalDataModel.personNameIsEditing = false
            },
            value = searchFieldValue, onValueChanged = { searchFieldValue = it },
            placeholder = "Введіть текст пошуку",
            placeholderTextColor = Color(0xFFECECEE).copy(alpha = 0.5f),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFFECECEE)
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(20.dp)
        )
    }
}

@Composable
fun ChatFeedContentSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.94f)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        MeetingTimerSection()
        ChatFeedSection()
    }
}


@Composable
fun MeetingTimerSection() {
    var viewDetails by remember { mutableStateOf<TextLayoutResult?>(null) }
    val meetingTimerStatus = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFE15241), fontSize = 16.sp)) {
            append("Залишилося: ")
        }
        withStyle(
            style = SpanStyle(
                color = Color(0xFFE59F30),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append("47")
        }
        withStyle(
            style = SpanStyle(
                color = Color(0xFFE15241),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append(" год.")
        }
        withStyle(
            style = SpanStyle(
                color = Color(0xFFE59F30),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append(" 54")
        }
        withStyle(
            style = SpanStyle(
                color = Color(0xFFE15241),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append(" хв.")
        }
    }

    Box(modifier = Modifier
        .padding(start = 4.dp, end = 4.dp)
        .fillMaxWidth()
        .height(90.dp)
        .drawWithContent {
            val gradientBrush = Brush.linearGradient(
                listOf(
                    Color(0xFFE15241).copy(alpha = 0.2f),
                    Color(0xFF1FD4A7).copy(alpha = 0.2f),
                )
            )
            drawRoundRect(
                brush = gradientBrush,
                size = Size(width = size.width, height = size.height),
                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx()),
                style = Stroke(width = 1.dp.toPx())
            )
            drawLine(
                color = Color(0xFFA132D0).copy(alpha = 0.2f),
                start = Offset(70f, 0f),
                end = Offset(size.width - 50, 0f),
                strokeWidth = 1.dp.toPx()
            )
            drawContent()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Таймер зустрічі активований!",
                color = Color(0xFFECECEE),
                fontSize = 16.sp,
                modifier = Modifier
            )

            Text(
                meetingTimerStatus,
                textAlign = TextAlign.Center
            )

            Row {
                Text(
                    text = "Деталі",
                    color = Color(0xFFF07C58),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    onTextLayout = {
                        viewDetails = it
                    },
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .drawBehind {

                            viewDetails?.let {
                                val thickness = 3f
                                val dashPath = 5f
                                val spacingExtra = 2f
                                val offsetY = -8f

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
}


@Composable
fun ChatFeedSection() {
    val scope = rememberCoroutineScope()
    val muteChatSnackbarHostState = remember { SnackbarHostState() }
    val makeChatPrivateSnackbarHostState = remember { SnackbarHostState() }
    val deleteChatSnackbarHostState = remember { SnackbarHostState() }

    Spacer(modifier = Modifier.height(8.dp))
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        contentPadding = PaddingValues(2.dp),
        content = {
            items(
                listOf(
                    "1111111",
                    "2222222",
                    "1111111",
                    "2222222",
                    "1111111"
                )
            ) { dataItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "03/01/24",
                        color = Color(0xFFA89F9D)
                    )
                }


                ChatFeedCard(
                    scope = scope,
                    makeChatPrivateSnackbarHostState = makeChatPrivateSnackbarHostState,
                    deleteChatSnackbarHostState = deleteChatSnackbarHostState,
                    dataItem = dataItem,
                )
            }
        }
    )

}


@Composable
fun ChatFeedCard(
    scope: CoroutineScope,
    makeChatPrivateSnackbarHostState: SnackbarHostState,
    deleteChatSnackbarHostState: SnackbarHostState,
    dataItem: String,
    modifier: Modifier = Modifier,
) {
    var borderGradientChatmateMessageCard = Brush.horizontalGradient(
        colors = listOf(Color(0xFFE15241).copy(alpha = 0.4f), Color(0xFF856BFE).copy(alpha = 0.4f))
    )
    var borderGradientMyMessageCard = Brush.horizontalGradient(
        colors = listOf(Color(0xFFE15241).copy(alpha = 0.4f), Color(0xFF3CE9BE).copy(alpha = 0.4f))
    )

    val imageBackgroundGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFB1B0B6), Color(0xFF616161))
    )

    val unreadMessageBackgroundGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF3CE9BE), Color(0xFF3A7375))
    )

    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }

    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            },
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFFA4A6AA),
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color(0xFF282A2E),
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (dataItem == "1111111") Arrangement.End else Arrangement.Start
        )
        {
            Surface(
                modifier = Modifier
                    .width(280.dp)
                    .padding(start = 2.dp, end = 2.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 8.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 15.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 80.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF20242A), Color(0xFF1B1F24))
                            )
                        )
                        .border(
                            width = 0.5.dp,
                            brush = if (dataItem == "1111111") borderGradientMyMessageCard else borderGradientChatmateMessageCard,
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 8.dp,
                                bottomEnd = 10.dp,
                                bottomStart = 15.dp
                            )
                        )
                        .indication(interactionSource, LocalIndication.current)
                        .pointerInput(true) {
                            detectTapGestures(
                                onLongPress = {
                                    isContextMenuVisible = true
                                    pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                                },
                                onPress = {
                                    val press = PressInteraction.Press(it)
                                    interactionSource.emit(press)
                                    tryAwaitRelease()
                                    interactionSource.emit(PressInteraction.Release(press))
                                }
                            )
                        }
                        .padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 4.dp)
                ) {
                    Column {
                        Text(
                            text = "Lorem ipsum dolor sit amet, incididunt ut labore et dolore magna aliqua. ",
                            color = Color.White
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "12:00",
                                color = Color(0xFFA89F9D)
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.done_all_icon),
                                "contentDescription",
                                modifier = Modifier
                                    .size(22.dp),
                                tint = Color(0xFF7E57C2),
                            )
                        }
                    }
                }
            }
        }

        DropdownMenu(
            modifier = Modifier.background(Color(0xFF212429)),
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            DropdownMenuItem(
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.reply_icon2),
                            "contentDescription",
                            modifier = Modifier
                                .offset(x = (-3).dp)
                                .size(22.dp),
                            tint = Color(0xFFB1B0B6),
                        )

                        Text(
                            text = "Reply",
                            color = Color(0xFFB1B0B6),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                    }
                },
                onClick = {
                }
            )

            DropdownMenuItem(
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.copy_icon),
                            "contentDescription",
                            modifier = Modifier
                                .offset(x = (-2).dp, y = (-4).dp)
                                .size(22.dp),
                            tint = Color(0xFF856BFE),
                        )

                        Text(
                            text = "Copy",
                            color = Color(0xFFB1B0B6),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                },
                onClick = {
                    scope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                        val result = makeChatPrivateSnackbarHostState
                            .showSnackbar(
                                message = "Текст повідомлення скопійовано",
                                actionLabel = "Закрити",
                                // Defaults to SnackbarDuration.Short
                                duration = SnackbarDuration.Short
                            )

                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                /* Handle snackbar action performed */
                            }

                            SnackbarResult.Dismissed -> {
                                /* Handle snackbar dismissed */
                            }
                        }
                    }
                    Log.d("ff", "##2")
                    isContextMenuVisible = false
                }
            )
            DropdownMenuItem(
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.trash_bin_icon),
                            "contentDescription",
                            modifier = Modifier
                                .offset(x = (-5).dp, y = (-2).dp)
                                .size(30.dp),
                            tint = Color(0xFFF44336),
                        )

                        Text(
                            text = "Видалити",
                            color = Color(0xFFF44336),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 3.dp)
                        )
                    }
                },
                onClick = {
                    scope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                        val result = deleteChatSnackbarHostState
                            .showSnackbar(
                                message = "Чат видалено",
                                actionLabel = "Закрити",
                                // Defaults to SnackbarDuration.Short
                                duration = SnackbarDuration.Short
                            )

                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                /* Handle snackbar action performed */
                            }

                            SnackbarResult.Dismissed -> {
                                /* Handle snackbar dismissed */
                            }
                        }
                    }
                    Log.d("ff", "##3")
                    isContextMenuVisible = false
                }
            )
        }
    }
}


@Composable
fun MessageInputField() {
    val sendMessageButtonGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF3A7375), Color(0xFF3CE9BE))
    )

    var text by remember {
        mutableStateOf("")
    }
    Row {
        CustomMultiLineHintTextField(
            value = text,
            onValueChanged = { text = it },
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(sendMessageButtonGradient)
                    .align(Alignment.CenterVertically)
            ) {
                IconButton(onClick = {
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.send_message_icon),
                        contentDescription = "Localized description",
                        tint = Color(0xFF5242A7),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(30.dp)
                            .offset(x = 2.dp)
                    )
                }
            }
        }
    }

}

@Composable
fun CustomMultiLineHintTextField(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    var isAddPictureIconVisible by remember { mutableStateOf(true) }
    var isMessageInputGreater3Line by remember { mutableStateOf(false) }
    val borderGradientMessageBox = Brush.horizontalGradient(
        colors = listOf(Color(0xFFE15241), Color(0xFF3CE9BE))
    )

    Row(
        modifier = Modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.86f),
            shape = RoundedCornerShape(26.dp)
        ) {
            Row(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        brush = borderGradientMessageBox,
                        shape = RoundedCornerShape(26.dp)
                    )
                    .background(Color(0xFF000000))
                    .padding(start = 14.dp, end = 14.dp, top = 4.dp, bottom = 4.dp)
                    .defaultMinSize(minHeight = 48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(if (isAddPictureIconVisible) 0.9f else 1f)
                ) {
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChanged,
                        cursorBrush = SolidColor(Color(0xFF31B795)),
                        maxLines = 5,
                        textStyle = TextStyle(
                            color = Color(0xFFF2F4F8),
                            fontSize = 14.sp,
                        ),
                        decorationBox = { innerTextField ->
                            Box(modifier = Modifier) {
                                if (value.isEmpty()) {
                                    isAddPictureIconVisible = true
                                    Text(
                                        text = "Message...",
                                        fontSize = 14.sp,
                                        color = Color(0xFF9A9CA2)
                                    )
                                } else {
                                    isAddPictureIconVisible = false
                                    innerTextField()
                                }

                            }
                        },
                    )
                }

                if (isAddPictureIconVisible) {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.picture_icon),
                            contentDescription = "Localized description",
                            tint = Color(0xFFEC407A),
                            modifier = Modifier
                                .size(22.dp)
                                .offset(y = (-1).dp)
                        )

                    }
                }
            }
        }
    }
}