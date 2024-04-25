package com.example.petmatch.model.data

sealed class SignUpUIEvent {

    data class FirstNameChange(val firstName:String) : SignUpUIEvent()
    data class LastNameChange(val lastName:String) : SignUpUIEvent()
    data class EmailNameChange(val email:String) : SignUpUIEvent()
    data class PasswordNameChange(val password:String) : SignUpUIEvent()

    data class GenderChange(val gender: String) : SignUpUIEvent()
    data class RoleChange(val role: String) : SignUpUIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status: Boolean) : SignUpUIEvent()
    object RegisterButtonClicked : SignUpUIEvent()

}