package com.taxicar.views.themes.customDarkColorSet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


var isDarkMode by mutableStateOf(true)

object CustomDarkColorSet {
    val burgerMenuColor = Color(0xFFECECEE)
    val topBarBackgroundColor = Color(0xFF111113)
    val topBarTextColor = Color(0xFFECECEE)
    val mapAreaBackgroundColor = Color(0xFFFBEB4E)
    val filterColorButton = Color(0xFF5242A7)
    val buttonFilterIconFirstGradient = Color(0xFF5242A7)
    val mainScreenbottomPart = Color(0xFF111113)
    val containerColorSearchFieldDepartPoint = Color(0xFF919094)
    val depatmentPointGradient1 = Color(0xFF919094)
    val depatmentPointGradient2 = Color(0xFF212121)
    val searchIconColor = Color(0xFF514699)
    val statusDatingOption1 = Color(0xFF3CE9BE)
    val statusDatingOption2 = Color(0xFFFFFFFF)
    val statusEditButton = Color(0xFF856BFE)
    val chatIconColor = Color(0xFF8E08FC)
    val departurePointPlaceholder = Color(0xFFE0E0E0)
}

data class ColorPalette(
    val burgerMenuColor: Color,
    val topBarBackgroundColor: Color,
    val topBarTextColor: Color,
    val mapAreaBackgroundColor: Color,
    val filterColorButton: Color,
    val buttonFilterIconFirstGradient: Color,
    val mainScreenbottomPart: Color,
    val containerColorSearchFieldDepartPoint: Color,
    val depatmentPointGradient1: Color,
    val depatmentPointGradient2: Color,
    val searchIconColor: Color,
    val statusDatingOption1: Color,
    val statusDatingOption2: Color,
    val statusEditButton: Color,
    val chatIconColor: Color,
    val departurePointPlaceholder: Color,
    val secondBackgroundColor: Color,
    val firstGrayColor: Color,
    val firstWhite: Color,
    val secondWhite: Color,
)

val darkColorSet = ColorPalette(
    burgerMenuColor = Color(0xFFECECEE),
    topBarBackgroundColor = Color(0xFF111113),
    topBarTextColor = Color(0xFFECECEE),
    mapAreaBackgroundColor = Color(0xFFFBEB4E),
    filterColorButton = Color(0xFF5242A7),
    buttonFilterIconFirstGradient = Color(0xFF5242A7),
    mainScreenbottomPart = Color(0xFF111113),
    containerColorSearchFieldDepartPoint = Color(0xFF919094),
    depatmentPointGradient1 = Color(0xFF919094),
    depatmentPointGradient2 = Color(0xFF212121),
    searchIconColor = Color(0xFF514699),
    statusDatingOption1 = Color(0xFF3CE9BE),
    statusDatingOption2 = Color(0xFFFFFFFF),
    statusEditButton = Color(0xFF856BFE),
    chatIconColor = Color(0xFF8E08FC),
    departurePointPlaceholder = Color(0xFFE0E0E0),

    //Background color set
    secondBackgroundColor = Color(0xFF000000), //0xFF000000
    //gray color (close cross, and other items
    firstGrayColor = Color(0xFF9E9DA3),

    //white color set
    firstWhite = Color(0xFFE0E0E0), //#E0E0E0
    secondWhite = Color(0xFFF5F5F5) //#f5f5f5
)

val lightColorSet = ColorPalette(
    burgerMenuColor = Color(0xFFECECEE),
    topBarBackgroundColor = Color(0xFF111113),
    topBarTextColor = Color(0xFFECECEE),
    mapAreaBackgroundColor = Color(0xFFFBEB4E),
    filterColorButton = Color(0xFF5242A7),
    buttonFilterIconFirstGradient = Color(0xFF5242A7),
    mainScreenbottomPart = Color(0xFF111113),
    containerColorSearchFieldDepartPoint = Color(0xFF919094),
    depatmentPointGradient1 = Color(0xFF919094),
    depatmentPointGradient2 = Color(0xFF212121),
    searchIconColor = Color(0xFF514699),
    statusDatingOption1 = Color(0xFF3CE9BE),
    statusDatingOption2 = Color(0xFFFFFFFF),
    statusEditButton = Color(0xFF856BFE),
    chatIconColor = Color(0xFF8E08FC),
    departurePointPlaceholder = Color(0xFFE0E0E0),
    //Background color set
    secondBackgroundColor = Color(0xFF000000), //0xFF000000
    //gray color (close cross, and other items
    firstGrayColor = Color(0xFF9E9DA3),
    //white color set
    firstWhite = Color(0xFFE0E0E0), //#E0E0E0
    secondWhite = Color(0xFFF5F5F5) //#f5f5f5
)

var isDarkTheme: Boolean = true
val carColorPalette: ColorPalette = if (isDarkTheme) darkColorSet else lightColorSet



