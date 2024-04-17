package com.example.petmatch.view.data.rules

data class LoginUIState(
    var email : String = "",
    var password : String = "",

    var emailError : Boolean = false,
    var passwordError : Boolean = false


)