package com.example.petmatch.model.data

sealed class UIEvent {
    data class  FirstNameChanged(val firstName:String) : UIEvent()
    data class  LastNameChanged(val lastName:String) : UIEvent()
    data class  EmailChanged(val email:String) : UIEvent()
    data class  PasswordChanged(val password:String) : UIEvent()
    data class GenderChanged(val gender: String) : UIEvent()
    data class RoleChanged(val role: String) : UIEvent()

    object RegisterBtn : UIEvent()


}