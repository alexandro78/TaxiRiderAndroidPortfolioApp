package com.taxicar.views.screens.online

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taxicar.R
import com.taxicar.views.dialogspopup.settingpopup.FreeTripPopupDialog
import com.taxicar.views.dialogspopup.settingpopup.PopupMeetingAgremmentDialog
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.MainScaffoldContainer

@Composable
fun DatingSettingsScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Налаштування _____")
        }
    }

    MainScaffoldContainer(
        headerText = headerText,
        navIcon = { DatingSettingsScreenHeadIconButton() },
        bodyContent = { DatingSettingsScreenContentSection() },
    )
}


@Composable
fun DatingSettingsScreenHeadIconButton() {
    val navController = LocalNavController.current
    IconButton(onClick = {
        navController.popBackStack()
    }) {
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
fun DatingSettingsScreenContentSection() {
//    val context = LocalContext.current
    var isPopupFreeTripDialogVisible by remember { mutableStateOf(false) }
    var isPopupMeetingAgreementDialogVisible by remember { mutableStateOf(false) }
    var dialTextLayout by remember { mutableStateOf<TextLayoutResult?>(null) }
    var openDatingCheck by remember { mutableStateOf(false) }
    var freeTripCheck by remember { mutableStateOf(false) }
    var freeTripCheckAgreement by remember { mutableStateOf(false) }
    val headInfo = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFCAC8C8), fontSize = 16.sp)) {
            append("Lorem ipsum dolor sit amet, consectetur adipiscing incididunt ut labore et dolore magna aliqua. ")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {
        Column {

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.13f)
                    .padding(start = 2.dp, end = 2.dp)
            ) {
                Text(
                    text = headInfo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 3.dp, end = 3.dp)
                    .background(
                        Color(0xFF302E2E),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Switch(
                    checked = openDatingCheck,
                    colors = SwitchDefaults.colors(
                        checkedBorderColor = Color(0xFF3CE9BE),
                        checkedIconColor = Color(0xFF3CE9BE),
                        checkedThumbColor = Color(0xFF3CE9BE),
                        checkedTrackColor = Color(0xFF2B2C2E),
                        uncheckedBorderColor = Color(0xFF8B8486),
                        uncheckedThumbColor = Color(0xFF8B8486),
                        uncheckedTrackColor = Color(0xFF2B2C2E),
                    ),
                    onCheckedChange = {
                        openDatingCheck = it
                    },
                    thumbContent = if (openDatingCheck) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    } else {
                        null
                    }
                )

                Text(
                    text = "Option 1",
                    color = Color(0xFFB6B0B0),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.68f)
                    .padding(start = 3.dp, end = 3.dp)
                    .border(0.5.dp, Color(0xFF856BFE), shape = RoundedCornerShape(4.dp))
                    .align(Alignment.CenterHorizontally),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.53f)
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.warning_icon),
                                "contentDescription",
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .size(32.dp),
                                tint = Color(0xFFC0760A),
                            )

                            Text(
                                text = "Lorem ipsum",
                                color = Color(0xFFFFFFFF),
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                                color = Color(0xFFE4DEDE),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "Деталі",
                                color = Color(0xFFF07C58),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .offset(y = (-2).dp)
                                    .drawBehind {
                                        drawLine(
                                            color = Color(0xFFF07C58),
                                            start = Offset(0f, size.height),
                                            end = Offset(size.width, size.height),
                                            strokeWidth = 1.5.dp.toPx(),
                                            cap = StrokeCap.Round,
                                            pathEffect = PathEffect.dashPathEffect(
                                                intervals = floatArrayOf(
                                                    1f, 4.dp.toPx(),
                                                ),
                                            ),
                                        )
                                    }
                                    .offset(x = (-1).dp, y = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFF302E2E),
                                shape = RoundedCornerShape(10.dp),
                            )
                            .padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Switch(
                            checked = freeTripCheck,
                            colors = SwitchDefaults.colors(
                                checkedBorderColor = Color(0xFF3CE9BE),
                                checkedIconColor = Color(0xFF3CE9BE),
                                checkedThumbColor = Color(0xFF3CE9BE),
                                checkedTrackColor = Color(0xFF2B2C2E),
                                uncheckedBorderColor = Color(0xFF8B8486),
                                uncheckedThumbColor = Color(0xFF8B8486),
                                uncheckedTrackColor = Color(0xFF2B2C2E),
                            ),
                            onCheckedChange = {
                                freeTripCheck = it
                                if (freeTripCheck) {
                                    isPopupFreeTripDialogVisible = true
                                }

                                if (!freeTripCheck) {
                                    freeTripCheckAgreement = false
                                }
                            },
                            thumbContent = if (freeTripCheck) {
                                {
                                    Icon(
                                        imageVector = Icons.Filled.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(SwitchDefaults.IconSize),
                                    )
                                }
                            } else {
                                null
                            }
                        )

                        Text(
                            text = "Option 2",
                            color = Color(0xFFB6B0B0),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 18.dp, top = 6.dp, bottom = 6.dp),
                            fontSize = 16.sp,
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFF302E2E),
                                shape = RoundedCornerShape(10.dp),
                            )
                            .padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Switch(
                            checked = freeTripCheckAgreement,
                            colors = SwitchDefaults.colors(
                                checkedBorderColor = Color(0xFF3CE9BE),
                                checkedIconColor = Color(0xFF3CE9BE),
                                checkedThumbColor = Color(0xFF3CE9BE),
                                checkedTrackColor = Color(0xFF2B2C2E),
                                uncheckedBorderColor = Color(0xFF8B8486),
                                uncheckedThumbColor = Color(0xFF8B8486),
                                uncheckedTrackColor = Color(0xFF2B2C2E),
                            ),
                            onCheckedChange = {
                                freeTripCheckAgreement = it
                                if(!freeTripCheckAgreement){
                                    freeTripCheck = false
                                }

                                if(freeTripCheckAgreement){
                                    isPopupMeetingAgreementDialogVisible = true
                                }
                            },
                            thumbContent = if (freeTripCheckAgreement) {
                                {
                                    Icon(
                                        imageVector = Icons.Filled.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(SwitchDefaults.IconSize),
                                    )
                                }
                            } else {
                                null
                            }
                        )

                        Text(
                            text = "suboption 2",
                            color = Color(0xFFB6B0B0),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 18.dp, top = 6.dp, bottom = 6.dp),
                            fontSize = 16.sp,
                        )
                    }

                    if (isPopupMeetingAgreementDialogVisible) {
                        PopupMeetingAgremmentDialog(
                            onDismissRequest = {
                                // Here you can add the logic for closing the dialog box
                                isPopupMeetingAgreementDialogVisible = false
                                freeTripCheckAgreement = false
                            },
                            onConfirmation = {
                                // Here you can add the logic for confirmation the dialog
                                isPopupMeetingAgreementDialogVisible = false
                                freeTripCheckAgreement = true
                                freeTripCheck = true
                            }
                        )
                    }

                    if (isPopupFreeTripDialogVisible) {
                        FreeTripPopupDialog(
                            onDismissRequest = {
                                // Here you can add the logic for closing the dialog box
                                isPopupFreeTripDialogVisible = false
                                freeTripCheck = false
                            },
                            onConfirmation = {
                                // Here you can add the logic for confirmation the dialog
                                isPopupFreeTripDialogVisible = false
                                freeTripCheckAgreement = true
                            }
                        )
                    }
                }
            }
        }
    }
}


