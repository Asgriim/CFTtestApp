package org.asgrim.cfttestapp.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.asgrim.cfttestapp.vm.AppViewModel
import org.asgrim.cfttestapp.vm.VMfactory

@Composable
fun MainScreen(
    factory: VMfactory,
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel(factory = factory)
) {
    val detailsList = appViewModel.list.collectAsState(initial = emptyList())

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Enter the first digits of a card number (BIN/IIN)", modifier = Modifier.padding(20.dp).offset(y = 20.dp), fontSize = 17.sp)
        MainInputBlock(){a -> appViewModel.add(a)}
        HistoryBlock(list = detailsList) {appViewModel.clear()}
    }
}