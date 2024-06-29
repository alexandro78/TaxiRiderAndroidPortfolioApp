package com.taxicar.views.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.taxicar.views.navigation.Navigation
import com.taxicar.views.ui.theme.TaxiCarTheme


class MainActivity : ComponentActivity() {
    private val mainScreenViewModel: com.taxicar.view.models.MainScreenViewModel by viewModels()
    private val carSearchViewModel: com.taxicar.view.models.CarSearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaxiCarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Navigation()
                }
            }

        }
    }
}

