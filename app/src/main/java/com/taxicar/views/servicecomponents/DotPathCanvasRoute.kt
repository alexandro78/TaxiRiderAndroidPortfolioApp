package com.taxicar.views.servicecomponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun dotPathCanvasRoute() {
    Canvas(
        modifier = Modifier
            .padding(start = 10.dp, top = 6.dp)
            .height(600.dp)
    ) {
        val circleRadius = 14f
        val circleCenterX = circleRadius + 8.dp.toPx()
        val circleCenterY = circleRadius + 8.dp.toPx()

        val pureLineLength = 38.dp.toPx()
        val initLineStart = Offset(circleCenterX, circleCenterY)

        drawCircle(
            color = Color(0xFFA29F9F),
            center = Offset(circleCenterX, circleCenterY),
            style = Stroke(1.dp.toPx()),
            radius = circleRadius
        )

        drawCircle(
            color = Color(0xFFA29F9F),
            center = Offset(circleCenterX, circleCenterY),
            style = Stroke(1.dp.toPx()),
            radius = 4f
        )

        var adressPoint = 3
        var i = 1;
        while (i <= adressPoint - 1) {

            drawLine(
                color = Color(0xFFA29F9F),
                start = Offset(
                    circleCenterX, if (i > 1) {
                        initLineStart.y + (pureLineLength * (i - 1)) + circleRadius
                    } else {
                        initLineStart.y + circleRadius
                    }
                ),
                end = Offset(
                    circleCenterX,
                    initLineStart.y + (pureLineLength * i) - circleRadius
                ),
                strokeWidth = 1.dp.toPx()
            )

            drawCircle(
                color = Color(0xFFA29F9F),
                center = Offset(
                    circleCenterX,
                    initLineStart.y + (pureLineLength * i)
                ),
                style = Stroke(1.dp.toPx()),
                radius = circleRadius
            )
            i++
        }
    }
}