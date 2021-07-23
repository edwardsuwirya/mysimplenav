package com.enigmacamp.mysimplenavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.mysimplenavigation.ui.NavigationCommand
import com.enigmacamp.mysimplenavigation.util.SingleLiveEvent

class TermConditionFragmentViewModel : ViewModel() {
    private var _navigationCommandLiveData = SingleLiveEvent<NavigationCommand>()

    val navigationCommandLiveData: LiveData<NavigationCommand>
        get() = _navigationCommandLiveData

    fun doExit() {
        _navigationCommandLiveData.postValue(NavigationCommand.Back)
    }
}