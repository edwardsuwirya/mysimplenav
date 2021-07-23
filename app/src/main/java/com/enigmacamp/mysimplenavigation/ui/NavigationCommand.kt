package com.enigmacamp.mysimplenavigation.ui

import android.net.Uri
import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val directions: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
    data class BackTo(val destinationId: Int) : NavigationCommand()
    data class DeepLink(val destinationDeep: Uri) : NavigationCommand()
    object ToRoot : NavigationCommand()
}