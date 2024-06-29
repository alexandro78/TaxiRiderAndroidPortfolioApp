package com.taxicar.views.screens.ridehistoryscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults.buttonElevation
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.MainScaffoldContainer


@Composable
fun RideHistoryScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Мої поїздки")
        }
    }

    MainScaffoldContainer(
        headerText = headerText,
        navIcon = { RideHistoryScreenHeadIconButton() },
        bodyContent = { RideHistoryScreenContentSection() },
    )
}


@Composable
fun RideHistoryScreenHeadIconButton() {
    val navController = LocalNavController.current
    IconButton(onClick = { navController.popBackStack() }) {
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
fun RideHistoryScreenContentSection() {
    val rideHistoryViewModel: com.taxicar.view.models.RideHistoryViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111113))
    ) {
        TopInfoBar()
        Spacer(modifier = Modifier.height(4.dp))
        LazyColumn {
            // Add 5 items
            items(5) { index ->
                if (rideHistoryViewModel.expandedItemId == index) {
                    ExpandedHistoryCard(index = index)
                } else {
                    CollapsedHistoryCard(index = index)
                }
            }
        }
    }
}

@Composable
fun TopInfoBar() {
    val topRideCancelRideInfo = Brush.horizontalGradient(
        colors = listOf(Color(0xFF56505B), Color(0xFF757687))
    )

    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(start = 4.dp, top = 4.dp, end = 3.dp)
                .weight(1f)
                .height(80.dp)
                .background(topRideCancelRideInfo, shape = RoundedCornerShape(6.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-2).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.sportcar_image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                )

                Column(modifier = Modifier) {
                    Text(
                        text = "Поїздки",
                        fontSize = 16.sp,
                        color = Color(0xFFBDBDBD),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-4).dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                    ) {
                        Text(
                            text = "8",
                            fontSize = 16.sp,
                            color = Color(0xFFFFEE58),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(x = (-4).dp)
                        )
                    }

                }
            }
        }

        Box(
            modifier = Modifier
                .padding(start = 3.dp, top = 4.dp, end = 4.dp)
                .weight(1f)
                .height(80.dp)
                .background(topRideCancelRideInfo, shape = RoundedCornerShape(6.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-2).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.cancel_icon),
                    "contentDescription",
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color(0xFFD0857D),
                )

                Column(modifier = Modifier) {
                    Text(
                        text = "Скасовано",
                        fontSize = 16.sp,
                        color = Color(0xFFBDBDBD),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-4).dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                    ) {
                        Text(
                            text = "3",
                            fontSize = 16.sp,
                            color = Color(0xFFD4B0A1),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(x = (-4).dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CollapsedHistoryCard(index: Int) {
    val rideHistoryViewModel: com.taxicar.view.models.RideHistoryViewModel = viewModel()
    val collapsedCardBackGround = Brush.horizontalGradient(
        colors = listOf(Color(0xFF56505B), Color(0xFF757687))
    )

    Box(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp)
            .fillMaxWidth()
            .background(collapsedCardBackGround, shape = RoundedCornerShape(6.dp))
    ) {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            onClick = {rideHistoryViewModel.expandedItemId = if(rideHistoryViewModel.expandedItemId != index) {index} else { -1} },
            shape = RoundedCornerShape(3.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp,
                pressedElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 130.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, start = 6.dp, end = 8.dp, bottom = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1.1f)
                ) {
                    Row(modifier = Modifier.padding(bottom = 6.dp)) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.location_pin_icon),
                            "contentDescription",
                            modifier = Modifier
                                .padding(end = 7.dp)
                                .size(17.dp),
                            tint = Color(0xFF1E3649),
                        )

                        Text(
                            text = "locate 1",
                            color = Color(0xFFBDBDBD),
                            modifier = Modifier,
                            fontSize = 16.sp
                        )
                    }

                    Row(modifier = Modifier) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.location_pin_icon),
                            "contentDescription",
                            modifier = Modifier
                                .padding(end = 7.dp)
                                .size(17.dp),
                            tint = Color(0xFF1E3649),
                        )

                        Text(
                            text = "locate 1",
                            color = Color(0xFFBDBDBD),
                            modifier = Modifier,
                            fontSize = 16.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(0.9f),
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "11-12-2023",
                            color = Color(0xFFBDBDBD),
                            modifier = Modifier.padding(end = 12.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )

                        Text(
                            text = "11:15",
                            color = Color(0xFFBDBDBD),
                            modifier = Modifier.padding(end = 4.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        modifier = Modifier.height(40.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.hryvnia_svg_icon),
                            "contentDescription",
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(36.dp)
                                .offset(y = 1.dp),
                            tint = Color(0xFF66BB6A),
                        )

                        ////////////////////////////////if payment is be card show icon below //////
//                        Image(
//                            painter = painterResource(id = R.drawable.color_credit_card_icon),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .padding(end = 10.dp)
//                                .size(32.dp)
//                        )

                        Text(
                            text = "200 грн.",
                            color = Color(0xFFBDBDBD),
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(end = 6.dp, bottom = 4.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "Завершено",
                            color = Color(0xFFFFFFFF),
                            modifier = Modifier
                                .background(Color(0xFF458084), shape = RoundedCornerShape(6.dp))
                                .padding(start = 8.dp, end = 6.dp, top = 5.dp, bottom = 5.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    }
                }
            }

        }
    }
}


@Composable
fun ExpandedHistoryCard(index: Int) {
    val rideHistoryViewModel: com.taxicar.view.models.RideHistoryViewModel = viewModel()
    val expandedCardBackGround = Brush.horizontalGradient(
        colors = listOf(Color(0xFF56505B), Color(0xFF757687))
    )

    Box(
        modifier = Modifier
            .padding(start = 4.dp, top = 3.dp, end = 4.dp)
            .fillMaxWidth()
            .background(expandedCardBackGround, shape = RoundedCornerShape(6.dp))
    ) {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            onClick = {rideHistoryViewModel.expandedItemId = if(rideHistoryViewModel.expandedItemId != index) {index} else { -1} },
            shape = RoundedCornerShape(3.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp,
                pressedElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 230.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp)
                        .background(Color(0xFF757686)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Деталі замовлення",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.color_calendar_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(24.dp)
                            .offset(y = (-1).dp)
                    )

                    Text(
                        text = "11-12-2023",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "11:15",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier.padding(start = 12.dp),
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.width(75.dp))

                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.hryvnia_svg_icon),
                        "contentDescription",
                        modifier = Modifier
                            .size(36.dp)
                            .offset(y = (-4).dp),
                        tint = Color(0xFF66BB6A),
                    )

                    Text(
                        text = "200 грн.",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier
                            .padding(start = 8.dp),
                        fontSize = 16.sp,

                        )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-12).dp)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "Driver:",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier.padding(end = 8.dp),
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Driver Name",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier,
                        fontSize = 16.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-8).dp)
                        .padding(start = 12.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.sportcar_svg_icon),
                        "contentDescription",
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(46.dp, 24.dp),
                        tint = Color(0xFFF7463E),
                    )

                    Text(
                        text = "Car model",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier,
                        fontSize = 16.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-8).dp)
                        .padding(start = 12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.license_plate_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(28.dp)
                            .offset(y = (-4).dp)
                    )

                    Text(
                        text = "AE2827OI",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier,
                        fontSize = 16.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-8).dp)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "Колір:",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier.padding(end = 6.dp),
                        fontSize = 16.sp
                    )

                    Text(
                        text = "чорний",
                        color = Color(0xFFBDBDBD),
                        modifier = Modifier,
                        fontSize = 16.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-6).dp)
                        .padding(bottom = 12.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    ElevatedButton(
                        elevation = buttonElevation(8.dp),
                        onClick = { },
                        colors = ButtonColors(
                            containerColor = Color(0xFFA96468),
                            contentColor = Color(0xFF8A8485),
                            disabledContainerColor = Color(0xFFA96468),
                            disabledContentColor = Color(0xFF8A8485),

                            ),
                        modifier = Modifier
                            .offset(y = 2.dp)
                            .padding(end = 12.dp),
                        shape = RoundedCornerShape(4.dp),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.trash_bin_icon),
                            contentDescription = "Localized description",
                            tint = Color(0xFF9C9798),
                            modifier = Modifier.offset(x = (-12).dp, y = (-2).dp),
                        )

                        Text(
                            text = "Видалити",
                            color = Color(0xFFB8B8B8),
                            modifier = Modifier.offset(y = (-1).dp),
                            fontSize = 16.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .offset(y = 5.dp)
                            .background(Color(0xFF757686), shape = RoundedCornerShape(6.dp))
                            .shadow(
                                2.dp,
                                spotColor = Color(0xFF5A5460),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(start = 8.dp, top = 6.dp, end = 8.dp, bottom = 6.dp)
                    ) {
                        Text(
                            text = "3.2 км",
                            color = Color(0xFFB8B8B8),
                            modifier = Modifier.offset(y = 1.dp)
                                .padding(2.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}