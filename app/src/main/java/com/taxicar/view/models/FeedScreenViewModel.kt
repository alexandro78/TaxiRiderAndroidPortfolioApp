package com.taxicar.view.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FeedScreenViewModel : ViewModel() {

    var isSerchHeadIconButtonClick by mutableStateOf(true)
    var clearChat by mutableStateOf(false)

}