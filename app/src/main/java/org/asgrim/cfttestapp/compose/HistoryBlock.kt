package org.asgrim.cfttestapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.asgrim.cfttestapp.data.CardDetails

@Composable
fun HistoryBlock(
    list: State<List<CardDetails>>,
    modifier: Modifier = Modifier,
    clearAll: () -> Unit
) {

    Column() {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "HISTORY", modifier = Modifier
                    .padding(1.dp)
                    .offset(x = 12.dp, y = 12.dp)
            )
            Button(
                onClick = { clearAll() },
                modifier = Modifier
                    .padding(2.dp)
                    .offset(x = 0.dp, y = 12.dp)
            ) {
                Text(text = "CLEAR")
            }

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(2.dp)
                .background(Color.DarkGray)
        )
        if (list.value.isNotEmpty()) {


            HistoryList(list = list)
        }
    }
}