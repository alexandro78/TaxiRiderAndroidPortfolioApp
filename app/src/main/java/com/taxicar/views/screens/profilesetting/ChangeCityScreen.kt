package com.taxicar.views.screens.profilesetting

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.MainScaffoldContainer
import kotlinx.coroutines.launch

@Composable
fun ChangeCityScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Оберіть місто")
        }
    }

    MainScaffoldContainer(
        headerText = headerText,
        navIcon = { ChangeCityScreenHeadIconButton() },
        bodyContent = { ChangeCityScreenContentSection() },
    )
}


@Composable
fun ChangeCityScreenHeadIconButton() {
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangeCityScreenContentSection() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    modifier = Modifier,
                    snackbarData = it,
                    containerColor = Color(0xFF26A69A),
                    contentColor = Color(0xFFF8F8F2),
                    actionColor = Color(0xFFE2F14E),
                    actionContentColor = DefaultShadowColor,
                    shape = RoundedCornerShape(10.dp),
                )
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF111113))
        ) {
            //1 Card start /////////
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(top = 4.dp, start = 6.dp, end = 6.dp),
                colors = CardColors(
                    containerColor = Color(0xFF212429),
                    contentColor = Color(0xFFE9ECF1),
                    disabledContainerColor = Color(0xFFEC407A),
                    disabledContentColor = Color(0xFF7E57C2),
                ),
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .clickable {
                            scope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                                val result = snackbarHostState
                                    .showSnackbar(
                                        message = "Місто обрано",
                                        actionLabel = "Продовжити",
                                        // Defaults to SnackbarDuration.Short
                                        duration = SnackbarDuration.Short
                                    )

                                when (result) {
                                    SnackbarResult.ActionPerformed -> {
                                        /* Handle snackbar action performed */
                                    }

                                    SnackbarResult.Dismissed -> {
                                        /* Handle snackbar dismissed */
                                    }
                                }
                            }
                        }
                        .fillMaxSize()
                        .padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Київ",
                        color = Color(0xFFC7C2C3),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }
            //////1 Card end //////////

            //2 Card start /////////
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(top = 4.dp, start = 6.dp, end = 6.dp),
                colors = CardColors(
                    containerColor = Color(0xFF212429),
                    contentColor = Color(0xFFE9ECF1),
                    disabledContainerColor = Color(0xFFEC407A),
                    disabledContentColor = Color(0xFF7E57C2),
                ),
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .clickable {
                            scope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                                val result = snackbarHostState
                                    .showSnackbar(
                                        message = "Місто обрано",
                                        actionLabel = "Продовжити",
                                        // Defaults to SnackbarDuration.Short
                                        duration = SnackbarDuration.Short
                                    )

                                when (result) {
                                    SnackbarResult.ActionPerformed -> {
                                        /* Handle snackbar action performed */
                                    }

                                    SnackbarResult.Dismissed -> {
                                        /* Handle snackbar dismissed */
                                    }
                                }
                            }
                        }
                        .fillMaxSize()
                        .padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Дніпро",
                        color = Color(0xFFC7C2C3),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }
            //////2 Card end //////////


            //3 Card start /////////
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(top = 4.dp, start = 6.dp, end = 6.dp),
                colors = CardColors(
                    containerColor = Color(0xFF212429),
                    contentColor = Color(0xFFE9ECF1),
                    disabledContainerColor = Color(0xFFEC407A),
                    disabledContentColor = Color(0xFF7E57C2),
                ),
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .clickable {
                            scope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                                val result = snackbarHostState
                                    .showSnackbar(
                                        message = "Місто обрано",
                                        actionLabel = "Продовжити",
                                        // Defaults to SnackbarDuration.Short
                                        duration = SnackbarDuration.Short
                                    )

                                when (result) {
                                    SnackbarResult.ActionPerformed -> {
                                        /* Handle snackbar action performed */
                                    }

                                    SnackbarResult.Dismissed -> {
                                        /* Handle snackbar dismissed */
                                    }
                                }
                            }
                        }
                        .fillMaxSize()
                        .padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Харків",
                        color = Color(0xFFC7C2C3),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }
            //////3 Card end //////////
        }
    }
}

