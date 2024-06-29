package com.taxicar.views.screens.routebuildscreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.themes.customDarkColorSet.CustomDarkColorSet
import com.taxicar.views.themes.customDarkColorSet.carColorPalette

@Composable
fun PickLocationOnMapScreen(searchLabel: String?) {
    val navController = LocalNavController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111113))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                if (searchLabel != null) {
                    PickLocateOnMapHeader(backArrow = {
                        navController.popBackStack()
                    }, searchLabel)
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(5f)
            ) {
                MapContainer()
                //////////////////////////temporary/////
                FloatingActionButton(
                    onClick = { /* do something */ },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-50).dp)
                        .size(60.dp),
                    shape = CircleShape,
                    containerColor = Color.Transparent
                ) {
                    var myLocatePoint = painterResource(id = R.drawable.my_locate_pointer)
                    Icon(
                        painter = myLocatePoint,
                        "Localized description",
                        tint = Color.Unspecified
                    )
                }
                /////////////////////////////////end temporary////////
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                PickedLocationOnMap()
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                SimpleConfirmationPickLocateButton()
            }
        }
    }
}


@Composable
fun MapContainer() {
    Image(
        painter = painterResource(id = R.drawable.map_area),
        contentDescription = stringResource(id = R.string.map_area_img),
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentScale = ContentScale.FillBounds
    )
}


@Composable
fun SimpleConfirmationPickLocateButton() {
    OutlinedButton(
        onClick = {
            println("Button Clicked")
            /*navController.navigate("datingSettingsScreen")*/
        },

        modifier = Modifier
            .height(70.dp)
            .width(270.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF6756C9)
        ),
        border = BorderStroke(
            0.5.dp,
            Color(0xFF00C2E0)
        )
    )
    {
        Text(
            text = "Підтвердити",
            color = Color(0xFFECECEE),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }

}


@Composable
fun PickLocateOnMapHeader(backArrow: () -> Unit, searchLabel: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        contentAlignment = Alignment.Center
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "Close",
                modifier = Modifier
                    .size(26.dp)
                    .clickable {
                        backArrow()
                    },
                tint = carColorPalette.firstGrayColor,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val headerTitle: String
            if (searchLabel == "Звідки їдете") {
                headerTitle = "Оберіть на карті звідки їдете"
            }
            else {
                if (searchLabel == "Введіть місце призначення") {
                    headerTitle = "Оберіть пункт призначення на карті"
                }
                else {
                    headerTitle = "Оберіть зупинку на карті"
                }
            }
            Text(
                text = headerTitle,
                color = carColorPalette.firstWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}


@Composable
fun PickedLocationOnMap() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            CustomDarkColorSet.depatmentPointGradient1,
                            CustomDarkColorSet.depatmentPointGradient2,
                        )
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row() {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Localized description",
                    modifier = Modifier
                        .size(42.dp)
                        .padding(start = 2.dp)
                        .align(Alignment.CenterVertically),
                    tint = CustomDarkColorSet.searchIconColor
                )
                Column {
                    Text(
                        text = "Обрана точка",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = carColorPalette.secondWhite,
                        ),
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .offset(y = (-2).dp),
                        fontWeight = FontWeight.Light
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, bottom = 16.dp),
                        contentAlignment = Alignment.TopStart
                    )
                    {
                        Text(
                            text = "Максима Дія 4",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = carColorPalette.secondWhite,
                            ),
                            modifier = Modifier
                                .padding(1.dp)
                        )
                    }

                }
            }
        }
    }
}