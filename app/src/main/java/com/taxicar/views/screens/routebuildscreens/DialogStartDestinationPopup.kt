package com.taxicar.views.screens.routebuildscreens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.navigation.isSwipeHandled
import com.taxicar.views.themes.customDarkColorSet.CustomDarkColorSet
import com.taxicar.views.themes.customDarkColorSet.carColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun DialogStartDestinationContent(dismissDialog: () -> Unit) {
    TopBarDialogPopup(dismissDialog = dismissDialog)
}


//dialog screen1
@Composable
fun CloseButton(dismissDialog: () -> Unit) {
    // Кнопка закриття
    IconButton(
        onClick = {
            // Сховати діалог при кліку на крестик
            dismissDialog()
        },
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Close",
            tint = carColorPalette.firstGrayColor
        )
    }
}


//dialog screen2
@Composable
fun TopBarDialogPopup(dismissDialog: () -> Unit) {
    Surface(
        modifier = Modifier
            .background(color = Color(0xFF222429)) //0xFF000000
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(carColorPalette.secondBackgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    CloseButton(dismissDialog = dismissDialog)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Оберіть точку відправки та зупинки",
                        color = carColorPalette.firstWhite
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                //MainContentArea
                MainContentArea()
            }
        }
    }
}


@Composable
fun MainContentArea() {
    val mainScreenViewModel: com.taxicar.view.models.MainScreenViewModel = viewModel()
    val navController = LocalNavController.current
    Column {
        //set of popup dialog @composable goes here////
        LocateField()
        Spacer(modifier = Modifier.height(10.dp))
        SearchPointField("Звідки їдете")
        StartPoint()
        AddNextStopButton()
        SearchPointField("Введіть наступну зупинку")
        NextStops()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Останні локації",
                color = Color(0xC4FFEB3B)
            )
        }
        SearchHistoryList()
//        SelectFinishDestinationButton()
        ConfirmationSwipeButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Обрати кінцеву точку",
            onSwipe = {
                navController.navigate("last_destination_point_screen")
                mainScreenViewModel.dismissDialog()
                // This block will be executed when the button is swiped
            }
        )
    }
}


@Composable
fun LocateField() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF919094), Color(0xFF212121))
                    )
                ),
        ) {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.blue_locate_dot_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 14.dp, bottom = 10.dp, start = 10.dp, end = 0.dp)
                        .size(32.dp)
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Визначити моє разташування",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFE0E0E0)
                    ) //#E0E0E0
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPointField(searchLabel: String) {
    val navController = LocalNavController.current
    Surface(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF919094), Color(0xFF212121))
                    )
                )
        )
        {
            var text by rememberSaveable { mutableStateOf("") }
            //inpunt field goes here///
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = CustomDarkColorSet.departurePointPlaceholder,
                    unfocusedTextColor = CustomDarkColorSet.departurePointPlaceholder
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                value = text,
                onValueChange = {
                    text = it
                },
                label = {
                    Text(
                        text = searchLabel,
                        color = carColorPalette.secondWhite,
                        fontSize = 16.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Filled.ArrowForward,
                        contentDescription = "Localized description",
                        tint = Color(0xFF1FD4A7)
                    )
                },
                trailingIcon = {
                    PickMapLocation {
                        navController.navigate("pick_location_point_screen/$searchLabel") //треба передати searchLabel аргументом
                    }
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .size(25.dp)
                            .offset(x = (-32).dp),
                        tint = Color(0xFF575759)
                    )
                },
            )
        }
    }
}


@Composable
fun PickMapLocation(onClick: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }

    val iconModifier = Modifier
        .size(if (isClicked) 25.dp else 22.dp)
        .offset(x = (2).dp)
        .clickable {
            isClicked = !isClicked
            onClick()
        }
        .animateContentSize()

    Icon(
        ImageVector.vectorResource(id = R.drawable.location_map_pin_icon),
        contentDescription = "Localized description",
        modifier = iconModifier,
        tint = Color(0xFF1FD4A7)
    )
}

@Composable
fun AddNextStopButton() {
    ElevatedButton(modifier = Modifier
        .padding(top = 8.dp, start = 5.dp, end = 5.dp, bottom = 8.dp)
        .fillMaxWidth()
        .height(45.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF222429),
        ),
        onClick = { }) {
        Icon(
            Icons.Filled.Add,
            "contentDescription",
            tint = Color(0xFF1FD4A7)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "Додати зупинку",
            color = Color(0xFF1FD4A7),
            fontSize = 16.sp
        )
    }
}

@Composable
fun NextStops() {
    Column() {
        NextStop()
        NextStop()
    }
}

@Composable
fun StartPoint() {
    StartPointLocate()
}

@Composable
fun StartPointLocate() {
    Row(
        modifier = Modifier
            .padding(start = 14.dp, top = 10.dp, bottom = 16.dp)
    ) {
        Icon(
            Icons.Filled.LocationOn,
            "contentDescription",
            tint = Color(0xFF9E9DA3)
        )
        Spacer(modifier = Modifier.width(26.dp))
        Text(
            text = "Точка 1",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF9E9DA3)
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.End
        ) {

            Spacer(modifier = Modifier.width(24.dp))

            Icon(
                Icons.Filled.Close,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(18.dp)
                    .offset(x = (-16).dp),
                tint = Color(0xFF646468)
            )
        }
    }
    Spacer(
        modifier = Modifier
            .height(0.8.dp)
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp)
            .background(Color(0xFF4B4B4C))
    )
}


@Composable
fun NextStop() {
    Row(
        modifier = Modifier
            .padding(start = 14.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Icon(
            ImageVector.vectorResource(id = R.drawable.map_pin_dot),
            "contentDescription",
            modifier = Modifier
                .size(20.dp),
            tint = Color(0xFF9E9DA3)
        )
        Spacer(modifier = Modifier.width(26.dp))
        Text(
            text = "Зупинка .......",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF9E9DA3)
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.End
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.grip_vertical_svgrepo_com),
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(18.dp)
                    .offset(x = (-8).dp),
                tint = Color(0xFF646468)
            )

            Spacer(modifier = Modifier.width(24.dp))

            Icon(
                Icons.Filled.Close,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(18.dp)
                    .offset(x = (-14).dp),
                tint = Color(0xFF646468)
            )
        }
    }
    Spacer(
        modifier = Modifier
            .height(0.8.dp)
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp)
            .background(Color(0xFF4B4B4C))
    )
}

@Composable
fun SearchHistoryList() {
    Column(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .background(Color(0xFF222429))
    ) {//0xFF212429
        LastFiveSearchItem()
        LastFiveSearchItem()
    }

}


@Composable
fun LastFiveSearchItem() {
    Row(
        modifier = Modifier
            .padding(start = 14.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Icon(
            ImageVector.vectorResource(id = R.drawable.history_locate_item),
            "contentDescription",
            tint = Color(0xFF9E9DA3)
        )
        Spacer(modifier = Modifier.width(26.dp))
        Text(
            text = "вул ......",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF9E9DA3)
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.End
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.arrow_up),
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(18.dp)
                    .offset(x = (-18).dp),
                tint = Color(0xFF646468)
            )
        }
    }
    Spacer(
        modifier = Modifier
            .height(0.8.dp)
            .fillMaxWidth()
            .background(Color(0xFF4B4B4C))
            .padding(start = 5.dp, end = 5.dp)
    )
}


@Composable
fun SelectFinishDestinationButton() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            shape = RoundedCornerShape(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF856BFE), Color(0xFF302969))
                        )
                    )
            )
            {
                Button(modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .fillMaxWidth()
                    .height(65.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
                    onClick = { }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            ImageVector.vectorResource(id = R.drawable.double_right_arrow),
                            "contentDescription",
                            modifier = Modifier
                                .size(22.dp)
                                .offset(x = (1).dp, y = 1.dp),
                            tint = Color(0xFFECECEE),
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Обрати місце прибуття",
                            color = Color(0xFFECECEE),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

        }
    }
}


//Swipe button//////////////////////////////////////
@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun ConfirmationSwipeButton(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    backgroundColor: Color = Color.Transparent,
    borderStroke: BorderStroke = BorderStroke(1.dp, Color(0xFF856BFE)),
    text: String,
    mainTextStyle: TextStyle = TextStyle(Color(0xFFECECEE), 20.sp),
    additionalText: String = "Проведіть праворуч",
    additionalTextStyle: TextStyle = TextStyle(Color(0xFFECECEE), 14.sp),
    onSwipe: () -> Unit
) {
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val textAlpha by animateFloatAsState(
        if (swipeableState.offset.value > 10f) (1 - swipeableState.progress.fraction) else 1f,
        label = ""
    )
    val iconAlpha by animateFloatAsState(
        if (swipeableState.offset.value > 10f) (1 - swipeableState.progress.fraction) else 1f,
        label = ""
    )

    if (swipeableState.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                if (swipeableState.currentValue == 1) {
                    onSwipe()
                    CoroutineScope(Dispatchers.Default).launch {
                        swipeableState.snapTo(0)
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = (-10).dp),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            shape = shape,
            color = backgroundColor,
            border = borderStroke,
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFF856BFE), Color(0xFF302969))
                        )
                    )
                    .padding(start = 10.dp, top = 9.dp, bottom = 9.dp),
            ) {
                var iconSize by remember { mutableStateOf(IntSize.Zero) }
                val maxWidth = with(LocalDensity.current) {
                    this@BoxWithConstraints.maxWidth.toPx() - iconSize.width
                }
                Box(
                    modifier = Modifier
                        .onGloballyPositioned {
                            iconSize = it.size
                        }
                        .swipeable(
                            state = swipeableState,
                            anchors = mapOf(
                                0f to 0,
                                maxWidth to 1
                            ),
                            thresholds = { _, _ -> FractionalThreshold(0.5f) },
                            orientation = Orientation.Horizontal
                        )
                        .offset {
                            IntOffset(swipeableState.offset.value.roundToInt(), 0)
                        }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.double_right_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 12.dp)
                            .size(22.dp)
                            .graphicsLayer(alpha = iconAlpha), // Apply alpha here
                        tint = Color.White
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        text = text,
                        style = mainTextStyle.copy(color = mainTextStyle.color.copy(alpha = textAlpha))
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        text = additionalText,
                        style = additionalTextStyle.copy(
                            color = additionalTextStyle.color.copy(
                                alpha = textAlpha
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SwitchIsSwipeHandled(changer: Boolean) {
    CompositionLocalProvider(
        isSwipeHandled provides changer // Устанавливаем новое значение true
    ) {
        Log.d("MyLog", isSwipeHandled.current.toString() + " вызвался композитион")
    }

}

// В вашей Composable-функции

//0xFF222429 #222429
//@Preview(showBackground = true)
//@Composable
//fun PreviweCmposable() {
//    LocateField()
//} Log.d("MyLog", "вызвался onSwipe из LastDestinationPointScreen.kt")