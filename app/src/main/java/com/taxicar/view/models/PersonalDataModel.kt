package com.taxicar.view.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PersonalDataModel : ViewModel() {
    var personNameIsEditing by mutableStateOf(false)
    var birthDateIsEditing by mutableStateOf(false)
    var genderIsEditing by mutableStateOf(false)
    var dialpadIsEditing by mutableStateOf(false)
    var emailIsEditing by mutableStateOf(false)
}