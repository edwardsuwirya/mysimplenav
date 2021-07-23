package com.enigmacamp.mysimplenavigation.ui.login

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.mysimplenavigation.ui.InternalDeepLink
import com.enigmacamp.mysimplenavigation.ui.NavigationCommand
import com.enigmacamp.mysimplenavigation.util.SingleLiveEvent

class LoginFragmentViewModel : ViewModel() {
    private var _navigationCommandLiveData = SingleLiveEvent<NavigationCommand>()

    val navigationCommandLiveData: LiveData<NavigationCommand>
        get() = _navigationCommandLiveData


    fun doAuthenticate() {
        _navigationCommandLiveData.postValue(
            NavigationCommand.To(
                LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                    "edi"
                )
            )
        )
    }

    fun doNavigateTermCondition() {
        val deepLink = InternalDeepLink.TERMCONDITION.toUri()
        _navigationCommandLiveData.postValue(NavigationCommand.DeepLink(deepLink))
    }

    fun doExit() {
        _navigationCommandLiveData.postValue(NavigationCommand.ToRoot)
    }
}