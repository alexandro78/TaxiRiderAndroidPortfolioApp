package com.taxicar.views.screens.routebuildscreens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.themes.customDarkColorSet.carColorPalette
import kotlin.math.roundToInt

@Composable
fun RouteCreateScreen() {
    val navController = LocalNavController.current
    Surface(
        modifier = Modifier
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Close",
                            tint = carColorPalette.firstGrayColor
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Маршрут створено",
                        color = carColorPalette.firstWhite
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CreateRouteContentArea()
            }
        }
    }
}


@Composable
fun CreateRouteContentArea() {
    Column {
        RouteSection()
        Spacer(modifier = Modifier.height(10.dp))
        OptionStatusSection("Статус опцій ***",
            { DatingOptionItem("Option 1 is active") },
            { DatingOptionItem("Option 2 is inactive") })

        Spacer(modifier = Modifier.height(30.dp))
        PriceSelection()
        SearchRideButtonSection()
    }
}


@Composable
fun RouteSection() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF222429))
        ) { //
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                StartLocate("Some start address")
                MiddleStopLocations("Some middle street 1")
                MiddleStopLocations("Some middle street 2")
                FinishLocations("Finish locate")
            }
        }
    }
}


@Composable
fun StartLocate(startLocation: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            Icons.Filled.Place,
            contentDescription = "Localized description",
            modifier = Modifier
                .align(Alignment.CenterVertically),
            tint = Color(0xFF4EA7F8)
        )
        Text(
            text = startLocation,
            style = TextStyle(
                fontSize = 16.sp,
                color = carColorPalette.secondWhite,
            ),
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            fontWeight = FontWeight.Light
        )
    }
}


@Composable
fun MiddleStopLocations(stopLocation: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.map_pin_dot),
            contentDescription = "Localized description",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(18.dp),
            tint = Color(0xFFC89C42)
        )
        Text(
            text = stopLocation,
            style = TextStyle(
                fontSize = 16.sp,
                color = carColorPalette.secondWhite,
            ),
            modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically),
            fontWeight = FontWeight.Light
        )
    }
}


@Composable
fun FinishLocations(finishLocation: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.blue_locate_dot_icon),
            contentDescription = "Localized description",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(18.dp),
            tint = Color(0xFF1FD4A7)
        )
        Text(
            text = finishLocation,
            style = TextStyle(
                fontSize = 16.sp,
                color = carColorPalette.secondWhite,
            ),
            modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically),
            fontWeight = FontWeight.Light
        )
    }
}


@Composable
fun OptionStatusSection(
    headerText: String,
    composableItem1: @Composable () -> Unit,
    optionComposableItem2: @Composable () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2D2D33))
                    .padding(start = 22.dp),
                horizontalArrangement = Arrangement.Start
            )
            {
                Text(
                    text = headerText,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = carColorPalette.secondWhite,
                    ),
                    modifier = Modifier
                        .padding(top = 6.dp, bottom = 10.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Normal
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF222429))
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    composableItem1()
                    optionComposableItem2() //OptionItem("Option 2 is inactive")
                }

            }
        }
    }
}


@Composable
fun DatingOptionItem(item: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        if (item == "Option 1 is active") {
            Icon(
                Icons.Filled.CheckCircle,
                contentDescription = "Localized description",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(22.dp),
                tint = Color(0xFF1FD4A7)
            )
            Text(
                text = item,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = carColorPalette.secondWhite,
                ),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Light
            )
        } else {
            Icon(
                Icons.Filled.Clear,
                contentDescription = "Localized description",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(18.dp),
                tint = Color(0xFF6B6A77)
            )
            Text(
                text = item,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF9E9DA1),
                ),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Light
            )
        }
    }
}


@Composable
fun PriceSelection() {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var price by remember { mutableStateOf("") }
    price = sliderPosition.toInt().toString()

    val autoPriceStatus = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFCAC8C8), fontSize = 18.sp)) {
            append("Сума: ")
        }
        withStyle(style = SpanStyle(color = Color(0xFF3CE9BE), fontSize = 18.sp)) {
            append("автоматично")
        }
    }

    val selectedPriceBySlider = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFCAC8C8), fontSize = 18.sp)) {
            append("Сума: ")
        }
        withStyle(style = SpanStyle(color = Color(0xFFFFA726), fontSize = 18.sp)) {
            append(price)
        }

        withStyle(style = SpanStyle(color = Color(0xFFCAC8C8), fontSize = 18.sp)) {
            append(" грн.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (price == "0") {
                Text(text = autoPriceStatus,
                    fontSize = 18.sp)
            } else {
                Text(
                    text = selectedPriceBySlider,
                    color = Color(0xFFE2D7DB),
                    fontSize = 18.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp),
        ) {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it.roundToInt().toFloat() },
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFFEC407A),
                    activeTrackColor = Color(0xFF64D1AA),
                    inactiveTrackColor = Color(0xFFAB47BC),
                ),
                valueRange = 0f..1000f
            )
        }
    }
}


@Composable
fun SearchRideButtonSection() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(38.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {

        Surface(
            modifier = Modifier
                .width(280.dp)
                .height(75.dp),
            shape = RoundedCornerShape(38.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF856BFE), Color(0xFF302969))
                        )
                    )
                    .border(
                        width = 2.dp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFE15241), Color(0xFF856BFE))
                        ),
                        shape = RoundedCornerShape(38.dp)
                    ),
            ) {
                SearchRideButton()
            }
        }
    }
}


@Composable
fun SearchRideButton(/*onClick: () -> Unit*/) {
    val navController = LocalNavController.current
    OutlinedButton(modifier = Modifier
        .fillMaxSize(),
        onClick = {
            navController.navigate("car_search_progress_screen")
            /*onClick()*/
        }) {
        Text(
            text = "Шукати авто",
            style = TextStyle(
                fontSize = 20.sp,
                color = carColorPalette.secondWhite,
            ),
            modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically),
            fontWeight = FontWeight.Normal
        )
    }
}