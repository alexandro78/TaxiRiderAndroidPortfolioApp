package com.taxicar.views.screens.routebuildscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.themes.customDarkColorSet.carColorPalette

@Composable
fun LastDestinationPointScreen() {
    MainComponent()
}


@Composable
fun MainComponent() {
    val navController = LocalNavController.current
    Surface(
        modifier = Modifier
            .background(color = Color(0xFF222429))
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
                    IconButton(
                        onClick = {
                            // Сховати екран вибору кінцевої точки
                            // при кліку на крестик
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
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Пункт призначення",
                        color = carColorPalette.firstWhite
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                ContentArea()
            }
        }
    }
}

@Composable
fun ContentArea() {
    val navController = LocalNavController.current
    Column {
        SearchPointField("Введіть місце призначення") //all parram we need in pick locate on map screen
        //to pass here
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Останні локації",
                modifier = Modifier.clickable {
                    // Navigate to "create_route_screen" when the text is clicked
                },
                color = Color(0xC4FFEB3B)
            )
        }
        SearchHistoryList()
            ConfirmationSwipeButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Створити маршрут",
                onSwipe = {
                    // Handle swipe action
                    navController.navigate("create_route_screen")
                }
            )
        }
    }
