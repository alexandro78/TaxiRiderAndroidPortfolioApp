package com.taxicar.views.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.screens.routebuildscreens.DialogStartDestinationContent
import com.taxicar.views.themes.customDarkColorSet.CustomDarkColorSet
import com.taxicar.views.themes.customDarkColorSet.carColorPalette
import kotlinx.coroutines.launch

///////////////////////////////////////////////////////////////////////////////////////////
//Main screen container with start content (map, 3 button, departure point/next stops,/////
//option status, edit option button ///////////////////////////////////////////////////////
//////////////////////////////// *** begin @Composable *** ////////////////////////////////
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainScreenViewModel: com.taxicar.view.models.MainScreenViewModel) {
    val navController = LocalNavController.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemStatus by remember { mutableStateOf(false) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)

            ) {
                ModalDrawerSheet(
                    drawerContentColor = Color(0xFFECECEE),
                    drawerContainerColor = Color(0xFF222429)
                ) {

                    Box {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Box(modifier = Modifier.padding(1.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.avatar_image),
                                    contentDescription = "Image Description",
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .size(100.dp)
                                        .clip(CircleShape)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically)
                            ) {
                                Column {
                                    Text(
                                        "Username",
                                        fontSize = 18.sp,
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .offset(y = 6.dp)
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Row() {
                                        Box(
                                            modifier = Modifier
                                                .offset(x = 10.dp, y = 4.dp)
                                                .border(
                                                    0.3.dp,
                                                    Color(0xFFA135D0),
                                                    shape = RoundedCornerShape(8.dp)
                                                )
                                                .padding(4.dp)

                                        ) {
                                            Row(
                                                modifier = Modifier,
                                                verticalAlignment = Alignment.Bottom
                                            ) {
                                                Icon(
                                                    imageVector = ImageVector.vectorResource(id = R.drawable.grade_star_icon),
                                                    "contentDescription",
                                                    modifier = Modifier
                                                        .size(28.dp),
                                                    tint = Color(0xFFFFA726),
                                                )
                                                Text(
                                                    "5.0",
                                                    fontSize = 18.sp,
                                                    modifier = Modifier
                                                        .padding(
                                                            start = 4.dp,
                                                            end = 2.dp,
                                                            top = 2.dp,
                                                            bottom = 2.dp
                                                        )
                                                )
                                            }
                                        }
                                        Box(
                                            modifier = Modifier
                                                .offset(x = 55.dp, y = 2.dp)
                                                .border(
                                                    0.2.dp,
                                                    Color(0xFF79767A),
                                                    shape = RoundedCornerShape(18.dp)
                                                )
                                                .clickable {
                                                    navController.navigate("personal_data_screen")
                                                }
                                        ) {
                                            Icon(
                                                imageVector = ImageVector.vectorResource(id = R.drawable.edit_icon),
                                                "contentDescription",
                                                modifier = Modifier
                                                    .padding(4.dp)
                                                    .size(18.dp),
                                                tint = Color(0xFF88878D),
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    HorizontalDivider()
                    NavigationDrawerItem(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 14.dp, end = 26.dp)
                            .height(44.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color(0xFF222429),
                            selectedContainerColor = Color(0xFFFFA726),
                            selectedTextColor = Color(0xFFECECEE),
                            unselectedTextColor = Color(0xFFECECEE),
                        ),

                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.city_icon),
                                "contentDescription",
                                modifier = Modifier
                                    .size(26.dp),
                                tint = Color(0xFF9E9DA3),
                            )
                        },
                        label = {
                            Column {
                                Text(text = "місто")
                                Text(
                                    text = "Дніпро",
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .offset(y = (-2).dp)
                                )
                            }
                        },
                        selected = selectedItemStatus,
                        onClick = {
                            selectedItemStatus = !selectedItemStatus
                            navController.navigate("change_city_screen")
                            selectedItemStatus = !selectedItemStatus
                        }
                    )

                    NavigationDrawerItem(
                        modifier = Modifier
                            .padding(top = 14.dp, start = 14.dp, end = 26.dp)
                            .height(40.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color(0xFF222429),
                            selectedContainerColor = Color(0xFFFF7043),
                            selectedTextColor = Color(0xFFECECEE),
                            unselectedTextColor = Color(0xFFECECEE),
                        ),
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.history_icon),
                                "contentDescription",
                                modifier = Modifier
                                    .size(26.dp),
                                tint = Color(0xFF9E9DA3),
                            )
                        },
                        label = {
                            Text(
                                text = "Поїздки",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(vertical = 2.dp)
                            )
                        },
                        selected = selectedItemStatus,
                        onClick = {
                            selectedItemStatus = !selectedItemStatus
                            navController.navigate("ride_history_screen")
                            selectedItemStatus = !selectedItemStatus}
                    )

                    NavigationDrawerItem(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 14.dp, end = 26.dp)
                            .height(40.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color(0xFF222429),
                            selectedContainerColor = Color(0xFFFF7043),
                            selectedTextColor = Color(0xFFECECEE),
                            unselectedTextColor = Color(0xFFECECEE),
                        ),
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.credit_card_icon),
                                "contentDescription",
                                modifier = Modifier
                                    .size(26.dp),
                                tint = Color(0xFF9E9DA3),
                            )
                        },
                        label = {
                            Text(
                                text = "Платежі",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(vertical = 2.dp)
                            )
                        },
                        selected = false,
                        onClick = { /*TODO*/ }
                    )

                    NavigationDrawerItem(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 14.dp, end = 26.dp)
                            .height(40.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color(0xFF222429),
                            selectedContainerColor = Color(0xFFFF7043),
                            selectedTextColor = Color(0xFFECECEE),
                            unselectedTextColor = Color(0xFFECECEE),
                        ),
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.settings_icon),
                                "contentDescription",
                                modifier = Modifier
                                    .size(26.dp),
                                tint = Color(0xFF9E9DA3),
                            )
                        },
                        label = {
                            Text(
                                text = "Налаштування",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(vertical = 2.dp)
                            )
                        },
                        selected = false,
                        onClick = { navController.navigate("profile_setting_screen") }
                    )

                    NavigationDrawerItem(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 14.dp, end = 26.dp)
                            .height(40.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color(0xFF222429),
                            selectedContainerColor = Color(0xFFFF7043),
                            selectedTextColor = Color(0xFFECECEE),
                            unselectedTextColor = Color(0xFFECECEE),
                        ),
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.notifications_icon),
                                "contentDescription",
                                modifier = Modifier
                                    .size(26.dp),
                                tint = Color(0xFFFFA726),
                            )
                        },
                        label = {
                            Text(
                                text = "Новини",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(vertical = 2.dp)
                            )
                        },
                        selected = false,
                        onClick = { /*TODO*/ }
                    )
                    // ...other drawer items
                }
            }
        }
    ) {
        // Screen content
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { /* do something */
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description",
                                tint = carColorPalette.burgerMenuColor
                            )
                        }
                    },
                    title = {
                        Text(
                            textAlign = TextAlign.Center,
                            text = "TAXICAR",
                            modifier = Modifier.fillMaxWidth()
                                .offset(x = (-24).dp)
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = CustomDarkColorSet.topBarBackgroundColor,
                        titleContentColor = CustomDarkColorSet.topBarTextColor,
                    )
                )
            },
            content = {
                MainColumnContainer(mainScreenViewModel = mainScreenViewModel)
            },
        )
    }


}
//////////////////////////////// *** end @Composable *** //////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////


//child (have nested composables for MainScreen
@Composable
fun MainColumnContainer(mainScreenViewModel: com.taxicar.view.models.MainScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.8f)
                .background(Color.Green)

        ) {
            Image(
                painter = painterResource(id = R.drawable.map_area),
                contentDescription = stringResource(id = R.string.map_area_img),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

//            FloatingActionButton(
//                onClick = { /* do something */ },
//                modifier = Modifier
//                    .align(Alignment.CenterStart)
//                    .offset(x = (8).dp, y = 5.dp),
//                shape = CircleShape,
//                containerColor = CustomDarkColorSet.filterColorButton
//            ) {
//                val imageVectorFilter =
//                    ImageVector.vectorResource(id = R.drawable.filter_icon)
//                Icon(
//                    imageVector = imageVectorFilter,
//                    "Localized description",
//                    tint = Color.White
//                )
//            }

            FloatingActionButton(
                onClick = { /* do something */ },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = (-8).dp, y = 100.dp)
                    .size(35.dp),
                shape = CircleShape,
                containerColor = Color.Transparent
            ) {
                val imageVectorMylocate =
                    ImageVector.vectorResource(id = R.drawable.my_locate_point)
                Icon(
                    imageVector = imageVectorMylocate,
                    "Localized description",
                    tint = Color.Black
                )
            }


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
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Red)

        ) {
            Surface(
            ) {
                Box(
                    modifier = Modifier
                        .background(CustomDarkColorSet.mainScreenbottomPart)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                    ) {

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            GoToStartDestinationPointDialog(mainScreenViewModel = mainScreenViewModel)
                        }

                        Spacer(modifier = Modifier.height(1.dp))

                        DatingOptionSection()
                    }
                }
            }
        }
    }
}


//////////////////////// *** child @Composable of GoToStartDestinationPointDialog *** //////////
@SuppressLint("RememberReturnType")
@Composable
fun SerchWhereYouGoing(mainScreenViewModel: com.taxicar.view.models.MainScreenViewModel) {
    var text by rememberSaveable { mutableStateOf(" ") }

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = CustomDarkColorSet.departurePointPlaceholder,
            unfocusedTextColor = CustomDarkColorSet.departurePointPlaceholder
        ),
        textStyle = TextStyle(fontSize = 18.sp),
        value = text,
        onValueChange = {
            text = it
        },
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            // works like onClick
                            mainScreenViewModel.showDialog()
                        }
                    }
                }
            },
        label = {
            Text(
                "Звідки їдете",
                color = carColorPalette.secondWhite,
                fontSize = 16.sp
            )
        },
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Localized description",
                modifier = Modifier.size(35.dp),
                tint = CustomDarkColorSet.searchIconColor
            )
        },
    )
}

//////////////////////// *** child @Composable of MainScreen *** //////////////////////////
@Composable
fun GoToStartDestinationPointDialog(mainScreenViewModel: com.taxicar.view.models.MainScreenViewModel) {
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
            )
    ) {
        SerchWhereYouGoing(mainScreenViewModel = mainScreenViewModel)
        DialogStartDestination(
            isDialogVisible = mainScreenViewModel.isDialogVisible.value,
            dismissDialog = { mainScreenViewModel.dismissDialog() }
        ) //Поки isDialogVisible false, компонент
    }
}


//////////////////////// *** child @Composable of GoToStartDestinationPointDialog *** /////////
@Composable
fun DialogStartDestination(
    isDialogVisible: Boolean,
    dismissDialog: () -> Unit
) {
    if (isDialogVisible) {
        Dialog(
            onDismissRequest = {
                dismissDialog()
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            // Вміст діалогу
            DialogStartDestinationContent(dismissDialog = dismissDialog)
        }
    }
}

@Composable
fun DatingOptionSection() {
    val navController = LocalNavController.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp),
        ) {
            Text(
                text = "Option 1 is On some text goes here",
                fontSize = 20.sp,
                color = CustomDarkColorSet.statusDatingOption1
            )
            Text(
                text = "Option 2 is off some text goes here",
                fontSize = 20.sp,
                color = CustomDarkColorSet.statusDatingOption2
            )
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {

        OutlinedButton(
            onClick = {
                navController.navigate("dating_settings_screen")
            },

            modifier = Modifier
                .height(35.dp)
                .width(160.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White
            ),
            border = BorderStroke(
                0.5.dp,
                CustomDarkColorSet.statusEditButton
            )
        )
        {
            Text("Edit option")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(end = 20.dp, bottom = 20.dp),
        contentAlignment = Alignment.CenterEnd,
    ) {

        FloatingActionButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (8).dp, y = 5.dp),
            shape = CircleShape,
            containerColor = Color.Transparent
        ) {

        }
    }
}


@Composable
fun PickNextAndIntermediateStops() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(
            onClick = {
                /*TO_DO*/
            },

            modifier = Modifier
                .height(70.dp)
                .width(300.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White
            ),
            border = BorderStroke(
                0.5.dp,
                CustomDarkColorSet.statusEditButton
            )
        )
        {
            Text(
                "Оберіть наступну зупинку",
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }

    Spacer(modifier = Modifier.height(25.dp))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(
            onClick = {
                /*TO_DO*/
            },

            modifier = Modifier
                .height(70.dp)
                .width(350.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White
            ),
            border = BorderStroke(
                0.5.dp,
                CustomDarkColorSet.statusEditButton
            )
        )
        {
            Text(
                "Оберіть кінцеву зупинку",
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}
