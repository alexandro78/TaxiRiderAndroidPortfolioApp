package com.taxicar.views.screens.personaldata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.taxicar.R
import com.taxicar.views.navigation.LocalNavController
import com.taxicar.views.scaffoldcontainer.MainScaffoldContainer
import com.taxicar.views.servicecomponents.CustomMultiLineTextField
import com.taxicar.views.servicecomponents.CustomSingleLineTextField
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun PersonalDataScreen() {
    val headerText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFBDBDBD), fontSize = 16.sp)) {
            append("Personal data")
        }
    }

    MainScaffoldContainer(headerText,
        navIcon = { PersonalDataHeadIconButton() },
        bodyContent = { PersonalDataContentSection() },
    )
}


@Composable
fun PersonalDataHeadIconButton() {
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
fun PersonalDataContentSection() {
    val personalDataModel: com.taxicar.view.models.PersonalDataModel = viewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {
        Column {
            ImageSection()
            BioSection(personalDataModel)
            PersonalFieldsSection(personalDataModel = personalDataModel)
        }
    }
}


@Composable
fun ImageSection() {
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
                    .padding(start = 10.dp, top = 18.dp, end = 10.dp, bottom = 10.dp)
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
                            .clip(RoundedCornerShape(8.dp))
                            .border(0.5.dp, Color(0xFF3CE9BE), shape = RoundedCornerShape(8.dp))
                            .background(imageBackgroundGradient)
                    )

                    IconButton(modifier = Modifier
                        .offset(x = 80.dp, y = 85.dp),
                        onClick = { /*navController.popBackStack()*/ }) {
                        Box(
                            modifier = Modifier
                                .background(addButtonGradient, shape = CircleShape)
                                .padding(6.dp)
                                .clip(CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Localized description",
                                tint = Color(0xFFE7D321),
                                modifier = Modifier
                                    .size(26.dp)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 14.dp, top = 8.dp, bottom = 4.dp)
            ) {
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur elit, sed do eiusmod tempor ",
                    fontSize = 16.sp,
                    color = Color(0xFF9E9DA3)
                )
            }
        }

    }
}

@Composable
fun BioSection(personalDataModel: com.taxicar.view.models.PersonalDataModel) {
    var text by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .padding(top = 20.dp, start = 6.dp, end = 6.dp)
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF212429))
    ) {

        Box(modifier = Modifier
            .onFocusChanged {
                if (it.isFocused) {
                    personalDataModel.personNameIsEditing = false
                    personalDataModel.birthDateIsEditing = false
                    personalDataModel.genderIsEditing = false
                    personalDataModel.dialpadIsEditing = false
                    personalDataModel.emailIsEditing = false
                }
            }) {
            CustomMultiLineTextField(
                customCursorStyle = SolidColor(Color.Green),
                titlehintTextColor = Color(0xFFECECEE).copy(alpha = 0.5f),
                descHintTextColor = Color(0xFFECECEE).copy(alpha = 0.5f),
                value = text,
                onValueChanged = { text = it },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFFECECEE)
                ),
                titlehintText = "Desc",
                descHintText = "Here goes some addition text",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(10))
                    .background(Color.Transparent)
                    .padding(16.dp),
                maxLines = 8
            )
        }
    }
}


@Composable
fun PersonalFieldsSection(personalDataModel: com.taxicar.view.models.PersonalDataModel) {
    var personNameField by remember { mutableStateOf("Person name") }
    var genderField by remember { mutableStateOf("male") }
    var dialpadField by remember { mutableStateOf("+380") }
    var emailField by remember { mutableStateOf("your email") }

    /////////////// date picker members /////////////////////
    var pickedData by remember {
        mutableStateOf(LocalDate.now())
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MM dd yyyy")
                .format(pickedData)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    ////////////////////////////////////////////////////////////

    Column(
        modifier = Modifier
            .padding(start = 6.dp, top = 12.dp)
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(14.dp)
        )

        Row(
            modifier = Modifier
                .clickable {
                    personalDataModel.personNameIsEditing = true
                    personalDataModel.birthDateIsEditing = false
                    personalDataModel.genderIsEditing = false
                    personalDataModel.dialpadIsEditing = false
                    personalDataModel.emailIsEditing = false
                }
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.person_icon),
                contentDescription = "Localized description",
                tint = Color(0xFF9E9DA3),
                modifier = Modifier
                    .padding(start = 2.dp)
                    .size(24.dp)
            )

            if (personalDataModel.personNameIsEditing) {
                CustomSingleLineTextField(
                    customCursorStyle = SolidColor(Color.Green),
                    hideTextField = {
                        personalDataModel.personNameIsEditing = false
                    },
                    value = personNameField, onValueChanged = { personNameField = it },
                    placeholder = "Ваше ім'я",
                    placeholderTextColor = Color(0xFFECECEE).copy(alpha = 0.5f),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = Color(0xFFECECEE)
                    ),
                    modifier = Modifier
                        .padding(start = 4.dp, end = 10.dp)
                        .fillMaxWidth()
                        .height(25.dp)
                        .clip(RoundedCornerShape(4))
                        .padding(start = 4.dp, top = 2.dp)
                        .drawBehind {
                            drawLine(
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 5f,
                                color = Color(0xFF1FD1C0)
                            )
                        }
                )

            } else {
                Text(
                    text = personNameField,
                    fontSize = 18.sp,
                    color = Color(0xFFECECEE),
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 1.dp)
                )
            }
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp),
        color = Color(0xFF9E9DA3),
        thickness = 0.8.dp
    )

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(14.dp)
    )

    Row(
        modifier = Modifier
            .clickable {
                dateDialogState.show()
                personalDataModel.personNameIsEditing = false
                personalDataModel.genderIsEditing = false
                personalDataModel.dialpadIsEditing = false
                personalDataModel.emailIsEditing = false
            }
            .fillMaxWidth()
            .padding(start = 10.dp, bottom = 4.dp, end = 4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.calendar_icon),
            contentDescription = "Localized description",
            tint = Color(0xFF9E9DA3),
            modifier = Modifier
                .padding(start = 2.dp)
                .size(24.dp)
        )
        Text(
            text = formattedDate,
            fontSize = 18.sp,
            color = Color(0xFFECECEE),
            modifier = Modifier
                .padding(start = 8.dp, bottom = 1.dp)
        )
        ////////////////// date picker view ////////////////////////////////////////
        MaterialDialog(
            backgroundColor = Color(0xFF222429),
            shape = RoundedCornerShape(10.dp),
            dialogState = dateDialogState,
            buttons = {
                positiveButton(
                    text = "Ок",
                    textStyle = TextStyle.Default.copy(
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFECECEE)
                    )
                )
                negativeButton(
                    text = "Відмінити",
                    textStyle = TextStyle.Default.copy(
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFECECEE)
                    )
                )
            }
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Оберіть дату народження",
                locale = Locale("uk", "UA"),
                colors = DatePickerDefaults.colors(
                    headerBackgroundColor = Color(0xFF8D78FE),
                    headerTextColor = Color.White,
                    calendarHeaderTextColor = Color(0xFFECECEE),
                    dateActiveBackgroundColor = Color(0xFF61B19E),
                    dateInactiveBackgroundColor = Color(0xFF222429),
                    dateActiveTextColor = Color(0xFFFFFFFF),
                    dateInactiveTextColor = Color(0xFFECECEE)
                ),
            )
            {
                pickedData = it
            }
        }
        ///////////////////////////////////////////////////////////////////////
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp),
        color = Color(0xFF9E9DA3),
        thickness = 0.8.dp
    )

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(14.dp)
    )

    Row(
        modifier = Modifier
            .clickable {
                personalDataModel.personNameIsEditing = false
                personalDataModel.birthDateIsEditing = false
                personalDataModel.genderIsEditing = true
                personalDataModel.dialpadIsEditing = false
                personalDataModel.emailIsEditing = false
            }
            .fillMaxWidth()
            .padding(start = 10.dp, bottom = 4.dp, end = 4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.gender_symbols_icon),
            contentDescription = "Localized description",
            tint = Color(0xFF9E9DA3),
            modifier = Modifier
                .padding(start = 2.dp)
                .size(24.dp)
        )

        if (personalDataModel.genderIsEditing) {
            CustomSingleLineTextField(
                customCursorStyle = SolidColor(Color.Green),
                hideTextField = {
                    personalDataModel.genderIsEditing = false
                },
                value = genderField, onValueChanged = { genderField = it },
                placeholder = "Дата народження",
                placeholderTextColor = Color(0xFFECECEE).copy(alpha = 0.5f),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFECECEE)
                ),
                modifier = Modifier
                    .padding(start = 4.dp, end = 10.dp)
                    .fillMaxWidth()
                    .height(25.dp)
                    .clip(RoundedCornerShape(4))
                    .padding(start = 4.dp, top = 2.dp)
                    .drawBehind {
                        drawLine(
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 5f,
                            color = Color(0xFF1FD1C0)
                        )
                    }
            )

        } else {
            Text(
                text = genderField,
                fontSize = 18.sp,
                color = Color(0xFFECECEE),
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 1.dp)
            )
        }
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp),
        color = Color(0xFF9E9DA3),
        thickness = 0.8.dp
    )

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(14.dp)
    )

    //4 Row info
    Row(
        modifier = Modifier
            .clickable {
                personalDataModel.personNameIsEditing = false
                personalDataModel.birthDateIsEditing = false
                personalDataModel.genderIsEditing = false
                personalDataModel.dialpadIsEditing = true
                personalDataModel.emailIsEditing = false
            }
            .fillMaxWidth()
            .padding(start = 10.dp, bottom = 4.dp, end = 4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.dialpad_icon),
            contentDescription = "Localized description",
            tint = Color(0xFF9E9DA3),
            modifier = Modifier
                .padding(start = 2.dp)
                .size(24.dp)
        )

        if (personalDataModel.dialpadIsEditing) {
            CustomSingleLineTextField(
                customCursorStyle = SolidColor(Color.Green),
                hideTextField = {
                    personalDataModel.dialpadIsEditing = false
                },
                value = dialpadField, onValueChanged = { dialpadField = it },
                placeholder = "Дата народження",
                placeholderTextColor = Color(0xFFECECEE).copy(alpha = 0.5f),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFECECEE)
                ),
                modifier = Modifier
                    .padding(start = 4.dp, end = 10.dp)
                    .fillMaxWidth()
                    .height(25.dp)
                    .clip(RoundedCornerShape(4))
                    .padding(start = 4.dp, top = 2.dp)
                    .drawBehind {
                        drawLine(
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 5f,
                            color = Color(0xFF1FD1C0)
                        )
                    }
            )

        } else {
            Text(
                text = dialpadField,
                fontSize = 18.sp,
                color = Color(0xFFECECEE),
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 1.dp)
            )
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp),
        color = Color(0xFF9E9DA3),
        thickness = 0.8.dp
    )

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(14.dp)
    )

    Row(
        modifier = Modifier
            .clickable {
                personalDataModel.personNameIsEditing = false
                personalDataModel.birthDateIsEditing = false
                personalDataModel.genderIsEditing = false
                personalDataModel.dialpadIsEditing = false
                personalDataModel.emailIsEditing = true
            }
            .fillMaxWidth()
            .padding(start = 10.dp, bottom = 4.dp, end = 4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.email_icon),
            contentDescription = "Localized description",
            tint = Color(0xFF9E9DA3),
            modifier = Modifier
                .padding(start = 2.dp)
                .size(24.dp)
        )

        if (personalDataModel.emailIsEditing) {
            CustomSingleLineTextField(
                customCursorStyle = SolidColor(Color.Green),
                hideTextField = {
                    personalDataModel.emailIsEditing = false
                },
                value = emailField, onValueChanged = { emailField = it },
                placeholder = "Дата народження",
                placeholderTextColor = Color(0xFFECECEE).copy(alpha = 0.5f),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFECECEE)
                ),
                modifier = Modifier
                    .padding(start = 4.dp, end = 10.dp)
                    .fillMaxWidth()
                    .height(25.dp)
                    .clip(RoundedCornerShape(4))
                    .padding(start = 4.dp, top = 2.dp)
                    .drawBehind {
                        drawLine(
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 5f,
                            color = Color(0xFF1FD1C0)
                        )
                    }

            )

        } else {
            Text(
                text = emailField,
                fontSize = 18.sp,
                color = Color(0xFFECECEE),
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 1.dp)
            )
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp),
        color = Color(0xFF9E9DA3),
        thickness = 0.8.dp
    )
}

