package com.taxicar.views.screens.online

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taxicar.views.dialogspopup.pinprocedures.ChangePINDialogPopup
import com.taxicar.views.dialogspopup.pinprocedures.CreatePINDialogPopup
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.MainScaffoldContainer


@Composable
fun MessengerSettingsScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Messenger setting")
        }
    }

    MainScaffoldContainer(
        headerText = headerText,
        navIcon = { MessengerSetingsScreenHeadIconButton() },
        action = { RightHeaderSpacer() },
        { MessengerSetingsScreenContentSection() },
    )
}

@Composable
fun RightHeaderSpacer() {
    Spacer(modifier = Modifier.width(18.dp))
}


@Composable
fun MessengerSetingsScreenHeadIconButton() {
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
fun MessengerSetingsScreenContentSection() {
    val PINInputViewModel: com.taxicar.view.models.PINInputViewModel = viewModel()
    var isCreatePINDialogVisible by remember { mutableStateOf(false) }
    var isChangePINDialogVisible by remember { mutableStateOf(false) }
    val createSecretChatPIN = "Створити PIN для таємних чатів"
    val changeSecretChatPIN = "Змінити PIN для таємних чатів"
    val getSecretChatInfo = "Дізнатися все про таємні чати"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 12.dp, end = 6.dp),
            verticalAlignment = Alignment.Bottom){

            ClickableText( AnnotatedString(createSecretChatPIN),
                onClick = {
                    isCreatePINDialogVisible = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFB6B0B0)
                )
            )
        }
        HorizontalDivider(modifier = Modifier.padding(start = 10.dp, end = 10.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 12.dp, end = 6.dp),
            verticalAlignment = Alignment.Bottom){

            ClickableText( AnnotatedString(changeSecretChatPIN),
                onClick = {
                    isChangePINDialogVisible = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFB6B0B0)
                )
            )
        }
        HorizontalDivider(modifier = Modifier.padding(start = 10.dp, end = 10.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 12.dp, end = 6.dp),
            verticalAlignment = Alignment.Bottom){

            ClickableText( AnnotatedString(getSecretChatInfo),
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFB6B0B0)
                )
            )
        }
        HorizontalDivider(modifier = Modifier.padding(start = 10.dp, end = 10.dp))


        if (isCreatePINDialogVisible) {
            CreatePINDialogPopup(
                PINInputViewModel = PINInputViewModel,
                onDismissRequest = {
                    // Here you can add the logic for closing the dialog box
                    isCreatePINDialogVisible = false
                },
                onConfirmation = {
                    // Here you can add the logic for confirmation the dialog
                    isCreatePINDialogVisible = false
                }
            )
        }


        if (isChangePINDialogVisible) {
            ChangePINDialogPopup(
                PINInputViewModel = PINInputViewModel,
                onDismissRequest = {
                    // Here you can add the logic for closing the dialog box
                    isChangePINDialogVisible = false
                },
                onConfirmation = {
                    // Here you can add the logic for confirmation the dialog
                        isChangePINDialogVisible = false
                }
            )
        }

    }
}
