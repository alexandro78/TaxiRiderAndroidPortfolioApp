package com.taxicar.views.servicecomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.JsonParser
import com.taxicar.view.models.CarSearchViewModel
import kotlinx.coroutines.delay

@Composable
fun AnimatedCircleDoneMark(carSearchViewModel: com.taxicar.view.models.CarSearchViewModel) {
    carSearchViewModel.isVisible
    LaunchedEffect(carSearchViewModel.isVisible.value) {
        if (carSearchViewModel.isVisible.value) {
            delay(1700)
            carSearchViewModel.checkMarkVisibleFalse()
        }
    }

    AnimatedVisibility(
        visible = carSearchViewModel.isVisible.value,
        enter = slideInVertically(
            initialOffsetY = { it / 2},
        ),
        exit = slideOutVertically()
    ) {
        drawPathAnimation(showCheckMark = carSearchViewModel.isVisible.value)
    }
}




@Composable
fun drawPathAnimation(showCheckMark: Boolean) {
    var startDrawCheckMark by remember { mutableStateOf(false) }
    val path = remember { androidx.compose.ui.graphics.Path() }
    var eventState by remember { mutableIntStateOf(0) }

    var targetIndexValue by remember { mutableIntStateOf(0) }

    val currentIndexValue by animateIntAsState(
        targetValue = targetIndexValue, animationSpec = tween(900, easing = LinearEasing),
        label = ""
    )
    val points = parsePoint()

    LaunchedEffect(showCheckMark) {
        delay(150)
        startDrawCheckMark = true
        targetIndexValue = points.size - 1
    }

    eventState = 1

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val centerX = size.width / 2 - 90f
        val centerY = size.height / 2 - 600f

        path.moveTo(points[0].x + centerX, points[0].y + centerY)

        for (i in 1..currentIndexValue) {
            val nextPoint = points[i]
            path.lineTo(nextPoint.x + centerX, nextPoint.y + centerY)
        }

        drawCircle(
            color = Color(0xFFB9B9B9),
            center = Offset(size.width / 2, size.height / 2 - 500),
            radius = 150f
        )

        if (startDrawCheckMark) {
            drawPath(
                color = Color(0xFF009688),
                path = path,
                style = Stroke(width = 4.dp.toPx())
            )
        }
    }
}


@Composable
fun Button(onClick: () -> Unit, enabled: Boolean, function: () -> Unit) {
    val carSearchViewModel: com.taxicar.view.models.CarSearchViewModel = viewModel()
    androidx.compose.material3.Button(onClick = { carSearchViewModel.checkMarkVisibleTrue() }) {
        Text("Показать")
    }
}



@Composable
fun parsePoint(): MutableList<Offset> {
    val jsonString =
        "[[145.8125,45.875],[134.1296157836914,59.10287666320801],[124.11653900146484,74.4218978881836],[114.10345077514648,89.74091339111328],[104.09037399291992,105.05992317199707],[94.07729721069336,120.37894439697266],[83.89733695983887,134.5104751586914],[70.04976272583008,122.54476165771484],[56.20218849182129,110.57903671264648],[41.13876533508301,102.14697265625],[44.465880393981934,116.24849700927734],[58.30408477783203,128.22505950927734],[72.14229202270508,140.20162200927734],[86.95780754089355,149.92388153076172],[98.20332145690918,135.97737884521484],[108.22872924804688,120.66641998291016],[118.25411987304688,105.35548400878906],[128.27952194213867,90.04452514648438],[138.30493545532227,74.73356628417969],[148.33032608032227,59.422624588012695]]"
    val jsonArray = JsonParser.parseString(jsonString).asJsonArray

    val points = remember { mutableStateListOf<Offset>() }

    for (i in 0 until jsonArray.size()) {
        var offset = jsonArray.get(i).asJsonArray
        points.add(Offset(offset.get(0).asFloat, offset.get(1).asFloat))
    }

    return points
}
