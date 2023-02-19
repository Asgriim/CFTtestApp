package org.asgrim.cfttestapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.asgrim.cfttestapp.data.CardDetailsRepository

class VMfactory (private val repository: CardDetailsRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppViewModel(repository) as T
    }
}