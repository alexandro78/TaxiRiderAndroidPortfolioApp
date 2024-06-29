package com.taxicar.views.screens.profilesetting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taxicar.R
import com.taxicar.views.dialogspopup.profilesetting.DeleteAccountDialog
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.MainScaffoldContainer

@Composable
fun ProfileSettingScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Profile settings")
        }
    }

    MainScaffoldContainer(headerText,
        navIcon = { ProfileSettingHeadIconButton() },
        bodyContent = { ProfileSettingContentSection() },
    )
}


@Composable
fun ProfileSettingHeadIconButton() {
    val navController = LocalNavController.current
    IconButton(onClick = { /*navController.popBackStack()*/ }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Localized description",
            tint = Color(0xFF616161),
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }
}


@Composable
fun ProfileSettingContentSection() {
//    val personalDataModel: PersonalDataModel = viewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {
        Column {
            ProfileImageSection()
            ProfileSettingFieldsSection(/*personalDataModel = personalDataModel*/)
        }
    }
}


@Composable
fun ProfileImageSection() {
    val addButtonGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFFF9800), Color(0xFFB8720A))
    )

    val imageBackgroundGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFB1B0B6), Color(0xFF616161))
    )

    Box(
        modifier = Modifier
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.22f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //1 image
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp, 120.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color(0xFF3CE9BE), shape = CircleShape)
                            .background(imageBackgroundGradient)
                    )

                    IconButton(modifier = Modifier
                        .offset(x = 80.dp, y = 85.dp),
                        onClick = { /*navController.popBackStack()*/ }) {
                        Box(
                            modifier = Modifier
                                .background(addButtonGradient, shape = CircleShape)
                                .padding(4.dp)
                                .clip(CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Localized description",
                                tint = Color(0xFFE7D321),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
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
                    color = Color(0xFFE2DFD9),
                    modifier = Modifier
                        .padding(
                            start = 4.dp,
                            end = 2.dp,
                            top = 2.dp,
                            bottom = 2.dp
                        )
                )
            }


            Box(
                modifier = Modifier
                    .padding(start = 14.dp, top = 8.dp, bottom = 4.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "NickName",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFFC4C3CF),
                    modifier = Modifier
                        .offset(x = (-8).dp)
                )
            }


            Box(
                modifier = Modifier
                    .padding(start = 14.dp, top = 8.dp, bottom = 4.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "050 239 4823",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF9A99A2),
                    modifier = Modifier
                        .offset(x = (-8).dp)
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 16.dp, start = 14.dp, end = 14.dp),
                thickness = 1.dp,
                color = Color(0xFF9A99A2)
            )
        }
    }
}


@Composable
fun ProfileSettingFieldsSection() {
    val navController = LocalNavController.current
    var isDialogDeleteAccountVisible by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(35.dp))



    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 14.dp, end = 14.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFF212429))
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.edit_profile),
                contentDescription = "Localized description",
                tint = Color(0xFF5242A7),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 12.dp, top = 6.dp)
                    .size(36.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Особисті дані",
                fontSize = 18.sp,
                color = Color(0xFFECECEE),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { navController.navigate("personal_data_screen") }
            )
        }
    }

    Spacer(modifier = Modifier.height(6.dp))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 14.dp, end = 14.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFF212429))
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.blacklist),
                contentDescription = "Localized description",
                tint = Color(0xFFE93323),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 12.dp)
                    .size(30.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Чорний список",
                fontSize = 18.sp,
                color = Color(0xFFECECEE),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        navController.navigate("black_list_screen")
                    }
            )
        }
    }


    Spacer(modifier = Modifier.height(6.dp))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 14.dp, end = 14.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFF212429))
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.change_password_icon),
                contentDescription = "Localized description",
                tint = Color(0xFFFFA726),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp, top = 8.dp)
                    .size(36.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Змінити пароль",
                fontSize = 18.sp,
                color = Color(0xFFECECEE),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        navController.navigate("change_password_screen")
                    }
            )
        }
    }


    Spacer(modifier = Modifier.height(6.dp))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 14.dp, end = 14.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFF212429))
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.trash_bin_icon),
                contentDescription = "Localized description",
                tint = Color(0xFFBF4E3A),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 12.dp)
                    .size(28.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Видалити акаунт",
                fontSize = 18.sp,
                color = Color(0xFFBF4E3A),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {isDialogDeleteAccountVisible = true}
            )
        }
    }

    if (isDialogDeleteAccountVisible) {
        DeleteAccountDialog(
            onDismissRequest = {
                // Here you can add the logic for closing the dialog box
                isDialogDeleteAccountVisible = false
            },
            onConfirmation = {
                // Here you can add the logic for confirmation the dialog
                isDialogDeleteAccountVisible = false
            }
        )
    }
}