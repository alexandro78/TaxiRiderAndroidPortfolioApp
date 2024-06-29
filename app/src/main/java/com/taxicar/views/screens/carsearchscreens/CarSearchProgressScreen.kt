package com.taxicar.views.screens.carsearchscreens

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.viewmodel.compose.viewModel

import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.servicecomponents.AnimatedCircleDoneMark
import com.taxicar.views.themes.customDarkColorSet.carColorPalette
import kotlinx.coroutines.delay

@Composable
fun CarSearchProgressScreen(carSearchViewModel: com.taxicar.view.models.CarSearchViewModel) {
    val navController = LocalNavController.current
    LaunchedEffect(key1 = true) {
        // Запускаем корутину для задержки в 5 секунд
        delay(5000)
        // Переходим по маршруту
//        navController.graph.startDestinationRoute?.let { navController.popBackStack(it, inclusive = false) }
//        navController.navigate("trip_suggested_screen")
        navController.navigate("trip_suggested_screen") {
            popUpTo(route = "car_search_progress_screen") { inclusive = true }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
        }

    }
    ContentScreen(carSearchViewModel = carSearchViewModel)
}

@Composable
fun ContentScreen(carSearchViewModel: com.taxicar.view.models.CarSearchViewModel) {
    var extendedCarSearch = remember { mutableStateOf(false) }
    ConstraintContainer(extendedCarSearch = extendedCarSearch)
    AnimatedCircleDoneMark(carSearchViewModel = carSearchViewModel)
}


private fun funConstrainSet(): ConstraintSet {
    return ConstraintSet {
        val textRef = createRefFor("textR")
        val horizontalguideline = createGuidelineFromTop(0.5f)

        constrain(textRef) {
            top.linkTo(horizontalguideline)
        }
    }
}

@Composable
fun ConstraintContainer(extendedCarSearch: MutableState<Boolean>) {
    Row(
        modifier = Modifier
            .padding(top = 180.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (extendedCarSearch.value) "Радіус міста увімкнено" else "",
            color = Color.Yellow,
            fontSize = 16.sp
        )
    }
    ConstraintLayout(funConstrainSet(), modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("textR"),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Іде пошук авто...",
                color = Color.White,
                fontSize = 18.sp
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            )
            {
                LinearProgressBarCarSearchSlider()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                ) {
                    StopSearchButton()
                }

                Box(
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                ) {
                    ExpandSearchRadius(extendedCarSearch = extendedCarSearch)
                }
            }
        }
    }
}


@Composable
fun LinearProgressBarCarSearchSlider() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatableSliderPosition = remember { Animatable(0f) }

    LaunchedEffect(infiniteTransition) {
        while (true) {
            animatableSliderPosition.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing), //FastOutSlowInEasing
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(14.dp)
        ) {
            drawRoundRect(
                color = Color(0xFF397174),
                size = Size(size.width, size.height),
                cornerRadius = CornerRadius(8.dp.toPx())
            )

            val sliderWidth = 300f
            val backgroundWidth = size.width - sliderWidth
            val currentPosition = backgroundWidth * animatableSliderPosition.value

            drawRoundRect(
                color = Color(0xFF1FD4A7),
                size = Size(sliderWidth, size.height),
                cornerRadius = CornerRadius(8.dp.toPx()),
                topLeft = Offset(currentPosition, 0f)
            )
        }
    }
}


@SuppressLint("RememberReturnType")
@Composable
fun ExpandSearchRadius(extendedCarSearch: MutableState<Boolean>) {
    val carSearchViewModel: com.taxicar.view.models.CarSearchViewModel = viewModel()
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .width(270.dp)
                .height(75.dp),
            shape = RoundedCornerShape(42.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF856BFE), Color(0xFF302969))
                        )
                    ),
            ) {
                OutlinedButton(modifier = Modifier
                    .width(270.dp)
                    .height(75.dp),
                    onClick = {
                        carSearchViewModel.checkMarkVisibleTrue()
                        extendedCarSearch.value = !extendedCarSearch.value
                    }) {
                    Text(
                        text = if (extendedCarSearch.value) "Пошук в місті" else "Шукати в межах міста",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = carColorPalette.secondWhite,
                        ),
                        modifier = Modifier,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}


@Composable
fun StopSearchButton() {
    val navController = LocalNavController.current
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.size(90.dp),  //avoid the oval shape
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Red),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
        ) {
            Text(
                text = "Stop",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
            )
        }
    }
}


