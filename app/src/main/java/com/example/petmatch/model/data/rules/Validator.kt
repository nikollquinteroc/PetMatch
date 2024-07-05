package com.example.petmatch.model.data.rules


object Validator {

    fun validateFirstName(fName: String?): ValidationResult {
        val regex = Regex("^[a-zA-ZáéíóúÁÉÍÓÚ]+(?: [a-zA-ZáéíóúÁÉÍÓÚ]+)*\$")
        return ValidationResult(
            !fName.isNullOrBlank() &&
                    fName.length in 2..31 &&
                    regex.matches(fName)
        )
    }

    fun validateLastName(lName: String?): ValidationResult {
        val regex = Regex("^[a-zA-ZáéíóúÁÉÍÓÚ]+(?: [a-zA-ZáéíóúÁÉÍÓÚ]+)*\$")
        return ValidationResult(
            !lName.isNullOrBlank() &&
                    lName.length in 2..31 &&
                    regex.matches(lName)
        )
    }


    fun validateEmail(email: String?): ValidationResult {
        val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return ValidationResult(
            !email.isNullOrBlank() &&
                    email.length in 8..31 &&
                    !email.contains(" ") &&
                    regex.matches(email)
        )
    }
    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 8)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue: Boolean):ValidationResult{
        return ValidationResult(
            statusValue
        )
    }

    fun validateGender(cboGender: String): ValidationResult {
        return ValidationResult(
            (!cboGender.isNullOrEmpty())
        )
    }

    fun validateRole(cboRol: String): ValidationResult {
        return ValidationResult(
            (!cboRol.isNullOrEmpty())
        )
    }

}

data class ValidationResult(
    val status: Boolean = false
)