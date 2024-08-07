package com.taxicar.views.scaffoldcontainer


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taxicar.views.themes.customDarkColorSet.CustomDarkColorSet


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffoldContainer(
    headerText: AnnotatedString,
    navIcon: @Composable (() -> Unit)? = null,
    action: @Composable (() -> Unit)? = null,
    bodyContent: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = CustomDarkColorSet.topBarBackgroundColor,
                    titleContentColor = CustomDarkColorSet.topBarTextColor,
                ),
                navigationIcon = {
                    navIcon?.let { it() }
                },
                actions = {
                    action?.let { it() }
                },
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = (-26).dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = headerText,
                            color = Color(0xFFECECEE),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            modifier = Modifier
                        )
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .padding(it)
            ) {
                bodyContent()
            }
        },
    )
}


