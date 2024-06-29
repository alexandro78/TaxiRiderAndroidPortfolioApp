package com.taxicar.view.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class RideHistoryViewModel : ViewModel() {
    var expandedItemId by mutableIntStateOf(-1)
}

//val rideHistoryViewModel: RideHistoryViewModel = viewModel()
//rideHistoryViewModel.expandedItemId = true