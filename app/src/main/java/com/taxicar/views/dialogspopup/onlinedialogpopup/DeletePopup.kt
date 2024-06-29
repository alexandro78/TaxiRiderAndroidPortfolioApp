package com.taxicar.views.dialogspopup.onlinedialogpopup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.taxicar.R

@Composable
fun AllChatsDeleteDialogPopup(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            border = BorderStroke(0.5.dp, Color.Red),
            shape = RoundedCornerShape(8.dp),
            colors = CardColors(
                containerColor = Color(0xFF212429),
                contentColor = Color(0xFFE1E4E9),
                disabledContainerColor = Color(0xFF212429),
                disabledContentColor = Color(0xFFE1E4E9)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier
                            .offset(x = (4).dp),
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Localized description",
                            tint = Color(0xFF8A8485),
                            modifier = Modifier
                                .size(28.dp),
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .offset(y = (-18).dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.trash_bin_icon),
                        contentDescription = "Localized description",
                        tint = Color(0xFFBF4E3A),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(32.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Видалити всі чати?!",
                        color = Color(0xFFB1B0B6),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 2.dp),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.offset(y = (-4).dp),
                    ) {
                        Text(
                            "Ok",
                            color = Color(0xFFFFCA28),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .border(1.dp, Color(0xFF3CE9BE))
                                .padding(6.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        "Всі чати будуть видалені назавжди!",
                        color = Color(0xFFB1B0B6),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(top = 30.dp)
                    )
                }
            }
        }
    }
}
