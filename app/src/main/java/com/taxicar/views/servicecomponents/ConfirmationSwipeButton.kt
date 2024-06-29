package com.taxicar.views.servicecomponents

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.taxicar.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


//Swipe button//////////////////////////////////////
@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun ConfirmationSwipeButtonComponent(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    backgroundColor: Color = Color.Transparent,
    borderStroke: BorderStroke = BorderStroke(1.dp, Color(0xFF856BFE)),
    text: String,
    timer: String,
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
                            .padding(start = 16.dp, top = 14.dp)
                            .size(22.dp)
                            .graphicsLayer(alpha = iconAlpha), // Apply alpha here
                        tint = Color.White
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 2.dp),
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
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                        .size(40.dp)
                        .border(1.dp, color = Color(0xFF5D4CB7), shape = RoundedCornerShape(20.dp))
                        .align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = "$timer",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 18.sp,
                        color = Color(0xFFFFFFFF)
                    )
                }

            }
        }
    }
}