package com.taxicar.view.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CarSearchViewModel : ViewModel() {
    private val _privatIsVisible = mutableStateOf(false)
    var isVisible: MutableState<Boolean> = _privatIsVisible

    fun checkMarkVisibleTrue() {
        _privatIsVisible.value = true
    }

    fun checkMarkVisibleFalse() {
        _privatIsVisible.value = false
    }
}