package com.example.petmatch.view.data.rules


object Validator {

    fun validateFirstName(fName: String?): validationResult {
        val regex = Regex("^[a-zA-ZáéíóúÁÉÍÓÚ]+(?: [a-zA-ZáéíóúÁÉÍÓÚ]+)*\$")
        return validationResult(
            !fName.isNullOrBlank() &&
                    fName.length in 2..31 &&
                    regex.matches(fName)
        )
    }

    fun validateLastName(lName: String?): validationResult {
        val regex = Regex("^[a-zA-ZáéíóúÁÉÍÓÚ]+(?: [a-zA-ZáéíóúÁÉÍÓÚ]+)*\$")
        return validationResult(
            !lName.isNullOrBlank() &&
                    lName.length in 2..31 &&
                    regex.matches(lName)
        )
    }


    fun validateEmail(email: String?): validationResult {
        val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return validationResult(
            !email.isNullOrBlank() &&
                    email.length in 8..31 &&
                    !email.contains(" ") &&
                    regex.matches(email)
        )
    }
    fun validatePassword(password: String): validationResult {
        return validationResult(
            (!password.isNullOrEmpty() && password.length >= 8)
        )
    }






    fun validateGender(cboGender: String): validationResult {
        return validationResult(
            (!cboGender.isNullOrEmpty())
        )
    }

    fun validateRole(cboRol: String): validationResult {
        return validationResult(
            (!cboRol.isNullOrEmpty())
        )
    }

}

data class validationResult(
    val status: Boolean = false
)