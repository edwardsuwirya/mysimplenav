package com.enigmacamp.transactionfeature.ui

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.navigation.InternalDeepLink
import com.enigmacamp.navigation.NavigationCommand
import com.enigmacamp.transactionfeature.R

class TransactionFragmentViewModel : ViewModel() {
    private var _navigationCommandLiveData =
        com.enigmacamp.coremodule.livedata.SingleLiveEvent<NavigationCommand>()

    val navigationCommandLiveData: LiveData<NavigationCommand>
        get() = _navigationCommandLiveData


    fun doSignOut() {
//        _navigationCommandLiveData.postValue(NavigationCommand.DeepLink(InternalDeepLink.LOGIN.toUri()))
        _navigationCommandLiveData.postValue(NavigationCommand.BackTo(R.id.loginFragment))
    }


    fun doExit() {
        _navigationCommandLiveData.postValue(NavigationCommand.Back)
    }
}