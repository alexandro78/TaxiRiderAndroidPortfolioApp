package com.taxicar.views.screens.carsearchscreens

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.servicecomponents.ConfirmationSwipeButtonComponent
import com.taxicar.views.servicecomponents.LinearProgressIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TripSuggestedScreen() {
//    val navController = LocalNavController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

        }
        ContentSectionTripSuggest()
    }
}


@Composable
fun ContentSectionTripSuggest() {
    HeaderBar()
    ImageSliderContainer()
    BottomSection()
}

@Composable
fun BottomSection() {
    val navController = LocalNavController.current
    var acceptTimer by remember { mutableIntStateOf(40) }

    LaunchedEffect(acceptTimer) {
        while (acceptTimer > 0) {
            delay(1000)
            acceptTimer -= 1
        }
    }

    Box(modifier = Modifier) {
        Column {
            Row(
                modifier = Modifier
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    acceptTimer = acceptTimer
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Special option",
                    color = Color(0xFF9E9DA3),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(14.dp)
                )

                OutlinedButton(
                    modifier = Modifier
                        .padding(top = 8.dp, end = 18.dp), onClick = {
                        navController.navigate("create_route_screen") {
                            popUpTo(route = "start_page") { inclusive = false }
                        }
//                        navController.graph.startDestinationRoute?.let {
//                            navController.popBackStack(
//                                it,
//                                inclusive = false
//                            )
//                        }
                    },
                    border = BorderStroke(0.4.dp, Color(0xFFEA3323))
                ) {
                    Text(
                        text = "Відмовитись",
                        color = Color(0xFFECECEE)
                    )
                }
            }

            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Авто: car brand. Номер: 6547",
                    color = Color(0xFF9E9DA3),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Колір: білий",
                    color = Color(0xFF9E9DA3),
                    fontSize = 18.sp,
                    modifier = Modifier
                )
            }

            Row(
                modifier = Modifier
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Приблизний час прибуття: 5 хв.",
                    color = Color(0xFF9E9DA3),
                    fontSize = 18.sp,
                    modifier = Modifier
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Driver comment about something",
                    color = Color(0xFFFF9800),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(18.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                //here goes accept swipe button
                ConfirmationSwipeButtonComponent(text = "Підтвердити",
                    timer = "$acceptTimer",
                    onSwipe = {
                        // Handle swipe action
                        navController.popBackStack()
                        navController.navigate("current_trip_screen")
                    })
            }
        }
    }
}

@Composable
fun HeaderBar() {
    Column {
        Row(
            modifier = Modifier
                .padding(start = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box() {
                Text(
                    text = "Driver name",
                    color = Color(0xFFF5F5F5),
                    fontSize = 18.sp
                )
            }

            Box(
                modifier = Modifier
                    .padding(start = 25.dp)
            ) {
                Row(
                    modifier = Modifier,
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
                        color = Color(0xFFF5F5F5),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(start = 4.dp, top = 3.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 25.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.trip_number),
                        "contentDescription",
                        modifier = Modifier
                            .size(26.dp),
                        tint = Color(0xFF2196F3),
                    )
                    Text(
                        text = "115",
                        color = Color(0xFFF5F5F5),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(start = 4.dp, top = 3.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun ImageSliderContainer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        ImageSlider()
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider() {
    val images = remember {
        mutableStateListOf(
            R.drawable.car1,
            R.drawable.car2,
            R.drawable.car3
        )
    }

    val pagerState = rememberPagerState(pageCount = {
        3
    })
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {

        Box(
            modifier = Modifier
                .size(350.dp, 370.dp)
                .drawBehind {
                    drawRoundRect(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFF9E82F0),
                                Color(0xFF42A5F5)
                            )
                        ),
                        cornerRadius = CornerRadius(16.dp.toPx())
                    )
                }
                .padding(2.dp)
                .align(Alignment.TopCenter),
        ) {
            HorizontalPager(state = pagerState) { page ->
                val pageOffset =
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                val imageSize by animateFloatAsState(
                    targetValue =
                    if (pageOffset != 0.0f) 0.75f else {
                        1f
                    },
                    animationSpec = tween(delayMillis = 300),
                    label = ""
                )
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .graphicsLayer {
                            scaleX = imageSize
                            scaleY = imageSize
                        },
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page])
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .offset(y = (-10).dp)
                    .fillMaxWidth(0.4f)
                    .clip(RoundedCornerShape(100))
                    .background(Color.Gray.copy(alpha = 0.5f))
                    .padding(4.dp)
                    .align(Alignment.BottomCenter)
            ) {
                IconButton(
                    onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) } },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Go back"
                    )
                }

                IconButton(
                    onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Go forward"
                    )
                }
            }
        }
    }
}

