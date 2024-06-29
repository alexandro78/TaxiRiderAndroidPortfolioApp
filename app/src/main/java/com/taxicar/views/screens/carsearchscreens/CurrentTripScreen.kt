package com.taxicar.views.screens.carsearchscreens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.SwipeableState
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.MainScaffoldContainer
import com.taxicar.views.servicecomponents.BottomSheetPopoup
import com.taxicar.views.servicecomponents.dotPathCanvasRoute
import kotlin.math.roundToInt

@Composable
fun CurrentTripScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Приблизний час прибуття ")
        }
        withStyle(
            style = SpanStyle(
                color = Color(0xFFE59F30),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append("15 хв.")
        }
    }

    MainScaffoldContainer(
        headerText = headerText,
        navIcon = { CurrentTripScreenHeadIconButton() },
        bodyContent = { CurentTripScreenContentSection() },
    )
}


@Composable
fun CurrentTripScreenHeadIconButton() {
    val navController = LocalNavController.current
    IconButton(onClick = {
        navController.navigate("start_page") {
            popUpTo("current_trip_screen") { inclusive = true }
        }
    }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Localized description",
            tint = Color(0xFF9E9DA3),
            modifier = Modifier
                .size(22.dp)
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun CurentTripScreenContentSection() {
    val context = LocalContext.current
    val swipeableState = rememberSwipeableState(initialValue = 0f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .swipeable(
                state = swipeableState,
                anchors = mapOf(
                    dpTopx(context = context, dpValue = 210f) to 0f,
                    -dpTopx(context = context, dpValue = 0f) to 1f
                ),
                thresholds = { _, _ -> FractionalThreshold(0.2f) },
                orientation = Orientation.Vertical
            )
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF111113))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .background(Color(0xFF009688))
            ) {
                TrackCarLocationMapSection()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CurrentTripInfoSection(swipeableState)
            }
        }
    }
}

@Composable
fun TrackCarLocationMapSection() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Map render API will be here",
            color = Color(0xFF000000),
            fontSize = 26.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CurrentTripInfoSection(swipeableState: SwipeableState<Float>) {
    Column(
    ) {
        DriverRatingSection()
        CurrentTripDashboard()
    }
    RouteInfoSection(swipeableState)
}


@Composable
fun DriverRatingSection() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            Row(
                modifier = Modifier
                    .offset(x = (-12).dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 1.dp)
                        .clip(RoundedCornerShape(16.dp)),

                    ) {
                    Text(
                        text = "Driver",
                        color = Color(0xFFCECACA),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .background(Color(0xFF5242A7))
                            .padding(top = 4.dp, bottom = 4.dp, start = 10.dp, end = 10.dp)
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Oleksandr",
                    color = Color(0xFFBDBDBD),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 5.dp)
                )
            }

            Row(
                modifier = Modifier
                    .offset(x = (-18).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.grade_star),
                    "contentDescription",
                    modifier = Modifier
                        .size(26.dp),
                    tint = Color(0xFFFFC107),
                )

                Text(
                    text = "5.0",
                    color = Color(0xFFBDBDBD),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 3.dp)
                )
            }

            Row(
                modifier = Modifier
                    .offset(x = (-18).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.trip_number),
                    "contentDescription",
                    modifier = Modifier
                        .size(26.dp),
                    tint = Color(0xFF2196F3),
                )
                Text(
                    text = "115",
                    color = Color(0xFFBDBDBD),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 3.dp)
                )
            }
        }
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentTripDashboard() {
//    val scope = rememberCoroutineScope()
    val contactButtonSheetState = rememberModalBottomSheetState()
    val optionButtonSheetState = rememberModalBottomSheetState()
    var showBottomContactButtonSheet by remember { mutableStateOf(false) }
    var showBottomOptionButttonSheet by remember { mutableStateOf(false) }
    val paymentInfo = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Готівка ")
        }
        withStyle(
            style = SpanStyle(
                color = Color(0xFF66BB6A),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append("100")
        }
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append(" грн.")
        }
    }

    val dashboardButtonGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF386F72), Color(0xFF3CE9BE))
    )

    val dashboardBackground = Brush.horizontalGradient(
        colors = listOf(Color(0xFF212429), Color(0xFF41464E))
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(dashboardBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.Center
        ) {

            ElevatedButton(modifier = Modifier
                .padding(6.dp)
                .size(width = 180.dp, height = 65.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(dashboardButtonGradient),
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                ),
                onClick = {
                    showBottomContactButtonSheet = true
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.chat_icon_sms),
                    "contentDescription",
                    modifier = Modifier
                        .offset(x = (-12).dp)
                        .size(26.dp),
                    tint = Color(0xFF000000),
                )

                Text(
                    "Зв'язатися",
                    fontSize = 20.sp,
                    modifier = Modifier
                )
            }

            Divider(
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .width(1.dp)
            )

            ElevatedButton(modifier = Modifier
                .padding(6.dp)
                .size(width = 180.dp, height = 65.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(dashboardButtonGradient),
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                ),
                onClick = {
                    showBottomOptionButttonSheet = true

                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.event_list_icon),
                    "contentDescription",
                    modifier = Modifier
                        .offset(x = (-12).dp)
                        .size(26.dp),
                    tint = Color(0xFF000000),
                )

                Text(
                    "Дії",
                    fontSize = 20.sp,
                    modifier = Modifier
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 4.dp),
        ) {
            Text(
                text = "Статус:",
                color = Color(0xFF9E9DA3),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 2.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Surface(
                modifier = Modifier
                    .padding(1.dp)
                    .border(
                        width = 1.dp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFE15241), Color(0xFF856BFE))
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ),
                color = Color.Transparent,
                border = BorderStroke(1.dp, Color.Transparent),
            ) {
                Text(
                    text = "подача авто",
                    color = Color(0xFF9E9DA3),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 1.dp, bottom = 2.dp, start = 10.dp, end = 10.dp)
                )
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.hryvnia_cash_icon),
            contentDescription = "",
            modifier = Modifier
                .size(44.dp)
        )

        Text(
            text = paymentInfo,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 14.dp)
                .align(Alignment.CenterVertically),
        )
    }

    BottomSheetPopoup(
        sheetState = contactButtonSheetState,
        showBottomSheet = showBottomContactButtonSheet,
        onDismissRequest = {
            showBottomContactButtonSheet = false
        }) {
        ComposableContactButtonBottomSheetView()
    }

    BottomSheetPopoup(
        sheetState = optionButtonSheetState,
        showBottomSheet = showBottomOptionButttonSheet,
        onDismissRequest = {
            showBottomOptionButttonSheet = false
        }) {
        ComposableOptionButtonBottomSheetView()
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun ComposableOptionButtonBottomSheetView() {
    var textLayout1 by remember { mutableStateOf<TextLayoutResult?>(null) }
    var textLayout2 by remember { mutableStateOf<TextLayoutResult?>(null) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.34f)
            .background(Color(0xFF32353C))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 40.dp)
                .offset(y = (-50).dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                //////////////////////////////////////////////////////////////////////////////////////
                Image(
                    painter = painterResource(id = R.drawable.red_close_circle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .offset(y = 6.dp)
                )
                Text(
                    text = "Скасувати поїздку",
                    color = Color.White,
                    fontSize = 18.sp,
                    onTextLayout = {
                        textLayout1 = it
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, bottom = 16.dp)
                        .drawBehind {

                            textLayout1?.let {
                                val thickness = 6f
                                val dashPath = 8f
                                val spacingExtra = 4f
                                val offsetY = 6f

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
                                        Color(0xFFE63E32),
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

            Row {
                //////////////////////////////////////////////////////////////////////////////////////
                Image(
                    painter = painterResource(id = R.drawable.chat_suppotr_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(48.dp)
                        .offset(x = (-8).dp, y = 2.dp)
                )
                Text(
                    text = "Написати в підтримку",
                    color = Color.White,
                    fontSize = 18.sp,
                    onTextLayout = {
                        textLayout2 = it
                    },
                    modifier = Modifier
                        .padding(start = 1.dp, top = 10.dp)
                        .drawBehind {

                            textLayout2?.let {
                                val thickness = 6f
                                val dashPath = 8f
                                val spacingExtra = 4f
                                val offsetY = 6f

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
                                        Color(0xFF42A5F5),
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
fun ComposableContactButtonBottomSheetView() {
    var dialTextLayout by remember { mutableStateOf<TextLayoutResult?>(null) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.34f)
            .background(Color(0xFF32353C))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 40.dp)
                .offset(y = (-50).dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                //////////////////////////////////////////////////////////////////////////////////////
                Image(
                    painter = painterResource(id = R.drawable.chat_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                )
                Text(
                    text = "Зв'язатись з водієм",
                    color = Color.White,
                    fontSize = 18.sp,
                    onTextLayout = {
                        dialTextLayout = it
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, top = 4.dp)
                        .drawBehind {

                            dialTextLayout?.let {
                                val thickness = 6f
                                val dashPath = 8f
                                val spacingExtra = 4f
                                val offsetY = 6f

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
                                        Color(0xFF29B6F6),
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


@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun RouteInfoSection(swipeableState: SwipeableState<Float>) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .offset {
            IntOffset(0, swipeableState.offset.value.roundToInt())
        }
        .background(Color(0xFF111113))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.drag_handle_icon2),
                "contentDescription",
                modifier = Modifier
                    .size(18.dp),
                tint = Color(0x68D3D0C7),
            )
        }
        Box(
            modifier = Modifier
        ) {

            dotPathCanvasRoute()

            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "first adress location",
                            color = Color(0xFFBDBDBD),
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(start = 16.dp, top = 3.dp, bottom = 3.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "second adress location",
                            color = Color(0xFFBDBDBD),
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(start = 16.dp, top = 3.dp, bottom = 3.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "last adress location",
                            color = Color(0xFFBDBDBD),
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(start = 16.dp, top = 3.dp, bottom = 3.dp)
                        )

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 22.dp)
                            .offset(x = (-14).dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedButton(modifier = Modifier
                            .size(width = 190.dp, height = 50.dp),
                            border = BorderStroke(0.4.dp, Color(0xFF9C27B0)),
                            onClick = { /*TODO*/ }) {
                            Text(
                                text = "Змінити маршрут",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }

                }
            }
        }
    }
}

private fun dpTopx(context: Context, dpValue: Float): Float {
    return dpValue * context.resources.displayMetrics.density
}