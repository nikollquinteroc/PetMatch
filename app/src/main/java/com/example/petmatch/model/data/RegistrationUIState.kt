package com.example.petmatch.model.data

data class RegistrationUIState(
    var firstName : String= "",
    var lastName: String= "",
    var email: String= "",
    var password: String= "",
    var gender: String= "",
    var role: String= "",
    var privacyPolicyAccepted : Boolean = false,


    var firstNameError : Boolean = false,
    var lastNameError: Boolean = false,
    var emailError : Boolean = false,
    var passwordError : Boolean = false,
    var genderError: Boolean = false,
    var roleError: Boolean = false,
    var privacyPolicyError: Boolean = false
)