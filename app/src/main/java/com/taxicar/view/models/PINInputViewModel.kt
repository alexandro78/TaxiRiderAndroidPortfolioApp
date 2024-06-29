package com.taxicar.view.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PINInputViewModel : ViewModel() {

    //Check secret PIN input dialog
    var wrongCheckPINInput by mutableStateOf(false)
    var isCheckPINTime by mutableStateOf(false)
    var checkPINInputSaveStatus by mutableStateOf(false)

    //Create secret PIN input dialog
    var wrongCreateRepeatPIN by mutableStateOf(false)

    //Change secret PIN input dialog
    var wrongOldChangePIN by mutableStateOf(false)
    var wrongChangeRepeatPIN by mutableStateOf(false)
}