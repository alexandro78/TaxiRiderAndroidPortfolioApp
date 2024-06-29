package com.taxicar.views.servicecomponents

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetPopoup(
    sheetState: SheetState,
    showBottomSheet: Boolean,
    onDismissRequest: () -> Unit,
    composableView: @Composable () -> Unit,

    ) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onDismissRequest.invoke()
            },
            containerColor = Color(0xFF212429),
            sheetState = sheetState,
            dragHandle = {
                BottomSheetDefaults.DragHandle(color = Color(0xFF9BA0A5))
            }
        ) {
                composableView()
            }
        }
    }
