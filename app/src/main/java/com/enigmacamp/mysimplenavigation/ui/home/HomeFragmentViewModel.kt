package com.enigmacamp.mysimplenavigation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.coremodule.livedata.SingleLiveEvent
import com.enigmacamp.navigation.NavigationCommand

class HomeFragmentViewModel : ViewModel() {
    private var _navigationCommandLiveData = SingleLiveEvent<NavigationCommand>()

    val navigationCommandLiveData: LiveData<NavigationCommand>
        get() = _navigationCommandLiveData


    fun doNavigateProfile() {
        _navigationCommandLiveData.postValue(NavigationCommand.To(HomeFragmentDirections.actionHomeFragmentToProfileFragment()))
    }

    fun doNavigateAboutUs() {
        _navigationCommandLiveData.postValue(NavigationCommand.To(HomeFragmentDirections.actionHomeFragmentToAboutUsFragment()))
    }

    fun doNavigateTransaction() {
        _navigationCommandLiveData.postValue(NavigationCommand.To(  HomeFragmentDirections.actionHomeFragmentToTransactionGraph()))
    }

    fun doExit() {
        _navigationCommandLiveData.postValue(NavigationCommand.Back)
    }
}