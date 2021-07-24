package com.enigmacamp.mysimplenavigation.ui.login

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.navigation.InternalDeepLink
import com.enigmacamp.navigation.NavigationCommand

class LoginFragmentViewModel : ViewModel() {
    private var _navigationCommandLiveData =
        com.enigmacamp.coremodule.livedata.SingleLiveEvent<NavigationCommand>()

    val navigationCommandLiveData: LiveData<NavigationCommand>
        get() = _navigationCommandLiveData


    fun doAuthenticate() {
        _navigationCommandLiveData.postValue(
            NavigationCommand.To(
                LoginFragmentDirections.actionLoginFragmentToHomeFragment("edi")
            )
        )
    }

    fun doNavigateTermCondition() {
        // Global action  ke TermConditionFragment di info_graph.xml, tidak bisa diakses dari nav_graph.xml
        // intinya global action hanya bisa diakses di dalam 1 navigation graph, tidak bisa lintas navigation
        // Makanya ada fitur yang dinamakan deep link
        val deepLink = InternalDeepLink.TERMCONDITION.toUri()
        _navigationCommandLiveData.postValue(
            com.enigmacamp.navigation.NavigationCommand.DeepLink(
                deepLink
            )
        )
    }

    fun doExit() {
        _navigationCommandLiveData.postValue(com.enigmacamp.navigation.NavigationCommand.ToRoot)
    }
}