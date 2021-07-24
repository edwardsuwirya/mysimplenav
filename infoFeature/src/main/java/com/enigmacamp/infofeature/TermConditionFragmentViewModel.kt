package com.enigmacamp.infofeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.navigation.NavigationCommand
import com.enigmacamp.coremodule.livedata.SingleLiveEvent

class TermConditionFragmentViewModel : ViewModel() {
    private var _navigationCommandLiveData = SingleLiveEvent<com.enigmacamp.navigation.NavigationCommand>()

    val navigationCommandLiveData: LiveData<com.enigmacamp.navigation.NavigationCommand>
        get() = _navigationCommandLiveData

    fun doExit() {
        _navigationCommandLiveData.postValue(com.enigmacamp.navigation.NavigationCommand.Back)
    }
}