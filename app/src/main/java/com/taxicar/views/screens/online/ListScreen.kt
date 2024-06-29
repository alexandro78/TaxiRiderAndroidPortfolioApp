package com.taxicar.views.screens.online

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taxicar.R
import com.taxicar.views.dialogspopup.onlinedialogpopup.AllChatsDeleteDialogPopup
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.ScaffoldContainerWithBottomBar
import com.taxicar.views.servicecomponents.CustomSearchBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ChatListScreen() {
    val allChatDeleteSnackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Lorem ipsum")
        }
    }

    val headerTextOff = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Lorem ipsum")
        }
    }

    val listScreenViewModel: com.taxicar.view.models.ListScreenViewModel = viewModel()

    ScaffoldContainerWithBottomBar(
        headerText = if (listScreenViewModel.isSerchHeadIconButtonClick) {
            headerText
        } else {
            headerTextOff
        },
        navIcon = {
            if (listScreenViewModel.isSerchHeadIconButtonClick) {
                ChatListScreenHeadIconButton()
            }
        },
        action = {
            ChatListHeadActionIconButton()
        },
        bottomBar = {
            BottomBar(
                scope = scope,
                allChatDeleteSnackbarHostState = allChatDeleteSnackbarHostState
            )
        },
        bodyContent = {
            ChatListScreenContentSection(
                scope = scope
            )
        }
    )
}


@Composable
fun ChatListScreenHeadIconButton() {
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
fun ChatListHeadActionIconButton() {
    val listScreenViewModel: com.taxicar.view.models.ListScreenViewModel = viewModel()

    if (listScreenViewModel.isSerchHeadIconButtonClick) {
        IconButton(onClick = {
            listScreenViewModel.isSerchHeadIconButtonClick = false
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
        SearchChatField()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatListScreenContentSection(
    scope: CoroutineScope
) {
//    val chatListScreenViewModel: ChatListScreenViewModel = viewModel()
    val muteChatSnackbarHostState = remember { SnackbarHostState() }
    val makeChatPrivateSnackbarHostState = remember { SnackbarHostState() }
    val deleteChatSnackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = muteChatSnackbarHostState) {
                Snackbar(
                    modifier = Modifier,
                    snackbarData = it,
                    containerColor = Color(0xFF909695),
                    contentColor = Color(0xFFFFEE58),
                    actionColor = Color(0xFFFFFFFF),
                    actionContentColor = DefaultShadowColor,
                    shape = RoundedCornerShape(10.dp),
                )
            }

            SnackbarHost(hostState = makeChatPrivateSnackbarHostState) {
                Snackbar(
                    modifier = Modifier,
                    snackbarData = it,
                    containerColor = Color(0xFFEC407A),
                    contentColor = Color(0xFFF5F5D8),
                    actionColor = Color(0xFFFFFFFF),
                    actionContentColor = DefaultShadowColor,
                    shape = RoundedCornerShape(10.dp),
                )
            }

            SnackbarHost(hostState = deleteChatSnackbarHostState) {
                Snackbar(
                    modifier = Modifier,
                    snackbarData = it,
                    containerColor = Color(0xFFEF5350),
                    contentColor = Color(0xFFF0E6E6),
                    actionColor = Color(0xFFFFFFFF),
                    actionContentColor = DefaultShadowColor,
                    shape = RoundedCornerShape(10.dp),
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF000000))
        )
        {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(3.dp),
                contentPadding = PaddingValues(2.dp),
                content = {
                    items(
                        listOf(
                            "1111111",
                            "2222222",
                            "3333333",
                            "8888888"
                        )
                    ) { dataItem ->
                        ChatListCard(
                            scope = scope,
                            muteChatSnackbarHostState = muteChatSnackbarHostState,
                            makeChatPrivateSnackbarHostState = makeChatPrivateSnackbarHostState,
                            deleteChatSnackbarHostState = deleteChatSnackbarHostState,
                            dataItem = dataItem,
                        )
                    }
                }
            )
        }
    }
}


@Composable
fun SearchChatField() {
    val listScreenViewModel: com.taxicar.view.models.ListScreenViewModel = viewModel()
    var searchFieldValue by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
    ) {

        IconButton(onClick = {
            listScreenViewModel.isSerchHeadIconButtonClick = true
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
            placeholder = "Введіть ім'я акаунта або PIN ",
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
fun ChatListCard(
    scope: CoroutineScope,
    muteChatSnackbarHostState: SnackbarHostState,
    makeChatPrivateSnackbarHostState: SnackbarHostState,
    deleteChatSnackbarHostState: SnackbarHostState,
    dataItem: String,
    modifier: Modifier = Modifier,
) {
    val navController = LocalNavController.current
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
            .height(100.dp)
            .drawWithContent {
                if (dataItem == "3333333" || dataItem == "1111111") {
                    drawLine(
                        color = Color(0xFF3CE9BE),
                        start = Offset(0f, 0f),  // начало линии в верхнем левом углу
                        end = Offset(0f, size.height),  // конец линии в нижнем левом углу
                        strokeWidth = 2.dp.toPx(),
                        cap = StrokeCap.Round,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(
                                0f, 6.dp.toPx(),
                            ),
                        ),
                    )
                }
                drawLine(
                    color = Color(0xFF5242A7),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx()
                )
                drawLine(
                    color = Color(0xFF5242A7),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
                drawContent()
            }
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
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
                        },
                        onTap = {
                            navController.navigate("chat_feed_screen")
                        }
                    )
                }
                .height(100.dp)
                .padding(6.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(6.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color(0xFF3CE9BE), shape = CircleShape)
                            .background(imageBackgroundGradient)
                    )
                }

                Column(modifier = Modifier.padding(10.dp)) {
                    Row(modifier = Modifier) {
                        Text(
                            text = dataItem,
                            color = Color(0xFFDDDDE0),
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        if (dataItem == "3333333" || dataItem == "8888888") {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.outline_lock_icon),
                                "contentDescription",
                                modifier = Modifier
                                    .size(22.dp)
                                    .offset(y = (-2).dp),
                                tint = Color(0xFF1FD4A7),
                            )
                        }
                    }

                    Text(
                        text = "Hi, how is it going?",
                        color = Color(0xFFB1B0B6),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Row {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.done_all_icon),
                            "contentDescription",
                            modifier = Modifier
                                .size(22.dp)
                                .offset(y = (-1).dp),
                            tint = Color(0xFF856BFE),
                        )

                        Text(
                            text = "Oct 09",
                            color = Color(0xFFB1B0B6),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (dataItem == "3333333" || dataItem == "1111111") {
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(30.dp)
                                .background(unreadMessageBackgroundGradient, shape = CircleShape),
                        ) {
                            Text(
                                text = "2",
                                color = Color(0xFFE95A49),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center),
                                textAlign = TextAlign.Center,
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
                            imageVector = ImageVector.vectorResource(id = R.drawable.mute_icon),
                            "contentDescription",
                            modifier = Modifier
                                .size(26.dp),
                            tint = Color(0xFFB1B0B6),
                        )

                        Text(
                            text = "Mute chat",
                            color = Color(0xFFB1B0B6),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                },
                onClick = {
                    scope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                        val result = muteChatSnackbarHostState
                            .showSnackbar(
                                message = "Звук чату вимкнено!",
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
                    Log.d("ff", "##1")
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
                            imageVector = ImageVector.vectorResource(id = R.drawable.lock_padlock),
                            "contentDescription",
                            modifier = Modifier
                                .offset(x = (-3).dp, y = (-4).dp)
                                .size(25.dp),
                            tint = Color(0xFF856BFE),
                        )

                        Text(
                            text = "Сховати",
                            color = Color(0xFFB1B0B6),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                },
                onClick = {
                    scope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                        val result = makeChatPrivateSnackbarHostState
                            .showSnackbar(
                                message = "Цей чат тепер таємний",
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
                                message = "Чат видалено!",
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun BottomBar(
    scope: CoroutineScope,
    allChatDeleteSnackbarHostState: SnackbarHostState,
) {
    val navController = LocalNavController.current
    val listScreenViewModel: com.taxicar.view.models.ListScreenViewModel = viewModel()
    var isAllChatsDeleteDialogPopupVisible by remember { mutableStateOf(false) }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = allChatDeleteSnackbarHostState) {
                Snackbar(
                    modifier = Modifier,
                    snackbarData = it,
                    containerColor = Color(0xFF856BFE),
                    contentColor = Color(0xFFECE4E4),
                    actionColor = Color(0xFFFFFFFF),
                    actionContentColor = DefaultShadowColor,
                    shape = RoundedCornerShape(10.dp),
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF000000))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(modifier = Modifier,
                    onClick = { isAllChatsDeleteDialogPopupVisible = true }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.trash_can1_icon),
                        contentDescription = "Localized description",
                        tint = Color(0xFF9E9DA3).copy(alpha = 0.6f),
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .size(32.dp),
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                IconButton(modifier = Modifier,
                    onClick = { navController.navigate("messenger_setting_screen") }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.settings_gear2_icon),
                        contentDescription = "Localized description",
                        tint = Color(0xFF9E9DA3).copy(alpha = 0.6f),
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .size(35.dp),
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))
            }

            if (isAllChatsDeleteDialogPopupVisible) {
                AllChatsDeleteDialogPopup(
                    onDismissRequest = {
                        // Here you can add the logic for closing the dialog box
                        isAllChatsDeleteDialogPopupVisible = false
                    },
                    onConfirmation = {

                        // Here you can add the logic for confirmation the dialog
                        listScreenViewModel.allChatDeletedSnackbarStatus = true
                        isAllChatsDeleteDialogPopupVisible = false
                    }
                )
            }

            if (listScreenViewModel.allChatDeletedSnackbarStatus) {
                scope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                    val result = allChatDeleteSnackbarHostState
                        .showSnackbar(
                            message = "Всі чати видалено!",
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
                listScreenViewModel.allChatDeletedSnackbarStatus = false
            }
        }
    }
}