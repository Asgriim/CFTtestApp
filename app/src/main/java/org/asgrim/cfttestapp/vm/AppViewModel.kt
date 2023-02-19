package org.asgrim.cfttestapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.asgrim.cfttestapp.data.CardDetails
import org.asgrim.cfttestapp.data.CardDetailsRepository

class AppViewModel(private val repository: CardDetailsRepository): ViewModel() {

    val list = repository.getCardDetails()

    fun add(cardDetails: CardDetails) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertCardDetails(cardDetails) }
    }

    fun remove(cardDetails: CardDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCardDetails(cardDetails)
        }
    }

    fun clear() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}