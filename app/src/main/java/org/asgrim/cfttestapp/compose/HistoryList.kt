package org.asgrim.cfttestapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.asgrim.cfttestapp.data.CardDetails

@Composable
fun HistoryList(
    list: State<List<CardDetails>>,
    modifier: Modifier = Modifier
){
    LazyColumn{
        items(
            items = list.value
        ){ item ->
                CardItem(cardDetails = item, modifier = Modifier.padding(12.dp).background(Color(255, 166, 77)))
        }
    }
}