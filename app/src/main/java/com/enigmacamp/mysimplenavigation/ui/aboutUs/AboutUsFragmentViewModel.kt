package com.enigmacamp.mysimplenavigation.ui.aboutUs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.mysimplenavigation.ui.NavigationCommand
import com.enigmacamp.mysimplenavigation.R
import com.enigmacamp.mysimplenavigation.util.SingleLiveEvent

class AboutUsFragmentViewModel : ViewModel() {
    private var _navigationCommandLiveData = SingleLiveEvent<NavigationCommand>()

    val navigationCommandLiveData: LiveData<NavigationCommand>
        get() = _navigationCommandLiveData


    fun doSignOut() {
        _navigationCommandLiveData.postValue(NavigationCommand.BackTo(R.id.loginFragment))
    }


    fun doExit() {
        _navigationCommandLiveData.postValue(NavigationCommand.Back)
    }
}