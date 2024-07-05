package com.example.petmatch.view.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object SignUpScreen : Screen()
    object TermsAndConditionsScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()
}

object  PostOfficeAppRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }
}