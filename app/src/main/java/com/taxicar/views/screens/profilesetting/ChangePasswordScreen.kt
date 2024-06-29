package com.taxicar.views.screens.profilesetting

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.MainScaffoldContainer
import com.taxicar.views.servicecomponents.validatePassword
import com.taxicar.views.themes.customDarkColorSet.carColorPalette

@Composable
fun ChangePasswordScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Змінити пароль")
        }
    }

    MainScaffoldContainer(
        headerText = headerText,
        navIcon = { ChangePasswordScreenHeadIconButton() },
        bodyContent = { ChangePasswordScreenContentSection() },
    )

}

@Composable
fun ChangePasswordScreenHeadIconButton() {
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
fun ChangePasswordScreenContentSection() {
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
                .padding(start = 6.dp, end = 6.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            var oldPassword by remember { mutableStateOf("") }
            var newPassword by remember { mutableStateOf("") }
            var repeatNewPassword by remember { mutableStateOf("") }
            var passwordIsVisible by remember { mutableStateOf(false) }
            var checkedOldpassword by remember { mutableStateOf("") }
            var checkedNewpassword by remember { mutableStateOf("") }
            var checkedNewRepeatpassword by remember { mutableStateOf("") }

            Text(
                text = checkedOldpassword,
                color = Color(0xFFEF5350),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )

            OutlinedTextField(
                value = oldPassword,
                onValueChange = { oldPassword = it.replace(Regex("[\\s\n]"), "") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF856BFE)
                ),
                label = {
                    Text(
                        "Старий пароль",
                        color = Color(0xFF9B9E9E),
                        fontSize = 16.sp
                    )
                },
                trailingIcon = {
                    val visibilityIcon = if (passwordIsVisible) {
                        ImageVector.vectorResource(id = R.drawable.visibility_on_icon)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.visibility_off_icon)
                    }

                    Icon(
                        imageVector = visibilityIcon,
                        "contentDescription",
                        modifier = Modifier
                            .clickable {
                                passwordIsVisible = !passwordIsVisible
                            }
                            .size(24.dp),
                        tint = Color(0xFF625F64),
                    )
                },
                visualTransformation = if (passwordIsVisible) VisualTransformation.None else PasswordVisualTransformation(
                    '●'
                ),
                keyboardOptions = KeyboardOptions(keyboardType = if (passwordIsVisible) KeyboardType.Text else KeyboardType.Password),
                placeholder = { Text("Введіть старий пароль") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = checkedNewpassword,
                color = Color(0xFFEF5350),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )

            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it.replace(Regex("[\\s\n]"), "") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF856BFE)
                ),
                trailingIcon = {
                    val visibilityIcon = if (passwordIsVisible) {
                        ImageVector.vectorResource(id = R.drawable.visibility_on_icon)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.visibility_off_icon)
                    }

                    Icon(
                        imageVector = visibilityIcon,
                        "contentDescription",
                        modifier = Modifier
                            .clickable {
                                passwordIsVisible = !passwordIsVisible
                            }
                            .size(24.dp),
                        tint = Color(0xFF625F64),
                    )
                },
                label = {
                    Text(
                        "Повторити новий пароль",
                        color = Color(0xFF9B9E9E),
                        fontSize = 16.sp
                    )
                },
                placeholder = { Text("Введіть новий пароль") },
                visualTransformation = if (passwordIsVisible) VisualTransformation.None else PasswordVisualTransformation(
                    '●'
                ),
                keyboardOptions = KeyboardOptions(keyboardType = if (passwordIsVisible) KeyboardType.Text else KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = checkedNewRepeatpassword,
                color = Color(0xFFEF5350),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )

            OutlinedTextField(
                value = repeatNewPassword,
                onValueChange = { repeatNewPassword = it.replace(Regex("[\\s\n]"), "") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF856BFE)
                ),
                trailingIcon = {
                    val visibilityIcon = if (passwordIsVisible) {
                        ImageVector.vectorResource(id = R.drawable.visibility_on_icon)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.visibility_off_icon)
                    }

                    Icon(
                        imageVector = visibilityIcon,
                        "contentDescription",
                        modifier = Modifier
                            .clickable {
                                passwordIsVisible = !passwordIsVisible
                            }
                            .size(24.dp),
                        tint = Color(0xFF625F64),
                    )
                },
                label = {
                    Text(
                        "Повторіть новий пароль",
                        color = Color(0xFF9B9E9E),
                        fontSize = 16.sp
                    )
                },
                visualTransformation = if (passwordIsVisible) VisualTransformation.None else PasswordVisualTransformation(
                    '●'
                ),
                keyboardOptions = KeyboardOptions(keyboardType = if (passwordIsVisible) KeyboardType.Text else KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF856BFE), Color(0xFF302969))
                                )
                            ),
                    ) {
                        OutlinedButton(modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                            shape = RoundedCornerShape(8.dp),
                            onClick = {
                                checkedOldpassword = validatePassword(oldPassword)
                                checkedNewpassword = validatePassword(newPassword)
                                checkedNewRepeatpassword = validatePassword(repeatNewPassword)
                            }) {
                            Text(
                                text = "Змінити пароль",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    color = carColorPalette.secondWhite,
                                ),
                                modifier = Modifier,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
