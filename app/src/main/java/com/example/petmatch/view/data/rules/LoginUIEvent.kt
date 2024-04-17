package com.example.petmatch.view.data.rules


sealed class LoginUIEvent {


    data class EmailChanged(val email:String) : LoginUIEvent()

    data class PasswordChanged(val password: String) : LoginUIEvent()

    object LoginButtonClicked : LoginUIEvent()
}