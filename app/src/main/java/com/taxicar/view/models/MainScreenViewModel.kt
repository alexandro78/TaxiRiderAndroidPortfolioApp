package com.taxicar.view.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    private val _isDialogVisible = mutableStateOf(false)
    val isDialogVisible: MutableState<Boolean> = _isDialogVisible

    fun showDialog() {
        _isDialogVisible.value = true
    }

    fun dismissDialog() {
        _isDialogVisible.value = false
    }
}