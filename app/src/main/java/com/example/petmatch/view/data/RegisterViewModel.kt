package com.example.petmatch.view.data



import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.petmatch.view.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuthUserCollisionException

import org.mindrot.jbcrypt.BCrypt


class RegisterViewModel : ViewModel() {
    private val TAG = RegisterViewModel::class.simpleName

    var registerUIState = mutableStateOf(RegisterUIState())

    var allValidationsPassed = mutableStateOf(false)

    var registrationStatus = mutableStateOf<RegistrationStatus?>(null)

    sealed class RegistrationStatus {
        object Success : RegistrationStatus()
        data class Error(val message: String) : RegistrationStatus()
    }

    fun onEvent(event: UIEvent) {
        validateDataRules()
        when (event) {
            is UIEvent.FirstNameChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    firstName = event.firstName
                )
                validateDataRules()

            }

            is UIEvent.LastNameChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    lastName = event.lastName
                )
                validateDataRules()

            }

            is UIEvent.EmailChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    email = event.email
                )
                validateDataRules()
            }
            is UIEvent.PasswordChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    password = event.password
                )
                validateDataRules()

            }

            is UIEvent.GenderChanged -> {
                registerUIState.value = registerUIState.value.copy(gender = event.gender)
            }

            is UIEvent.RoleChanged -> {
                registerUIState.value = registerUIState.value.copy(role = event.role)
            }
            is UIEvent.RegisterBtn -> {
                register()
            }
        }
    }

    private fun register() {
        createUserFirebase(
            email = registerUIState.value.email,
            password = registerUIState.value.password
        )
    }

    private fun validateDataRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registerUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registerUIState.value.lastName

        )
        val emailResult = Validator.validateEmail(
            email = registerUIState.value.email

        )
        val passwordResult = Validator.validatePassword(
            password = registerUIState.value.password

        )

        val genderResult = Validator.validateGender(
            cboGender = registerUIState.value.gender
        )
        val roleResult = Validator.validateRole(
            cboRol = registerUIState.value.role
        )

        registerUIState.value = registerUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            genderError = genderResult.status,
            roleError = roleResult.status
        )
        allValidationsPassed.value = (fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && genderResult.status
                && roleResult.status)

    }

 


    private fun createUserFirebase(email: String, password: String) {
        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, hashedPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user
                    firebaseUser?.let {
                        val user = hashMapOf(
                            "firstName" to registerUIState.value.firstName,
                            "lastName" to registerUIState.value.lastName,
                            "email" to registerUIState.value.email,
                            "password" to hashedPassword,
                            "gender" to registerUIState.value.gender,
                            "role" to registerUIState.value.role
                        )

                        FirebaseFirestore.getInstance().collection("users")
                            .document(it.uid)
                            .set(user)
                            .addOnSuccessListener {
                                showRegistrationSuccess()
                            }
                            .addOnFailureListener { e ->
                                showRegistrationError("${e.message}")
                            }
                    }
                } else {
                    task.exception?.let { exception ->
                        when (exception) {
                            is FirebaseAuthUserCollisionException -> {

                                showRegistrationError("Email already exists")
                            }
                            else -> {

                                showRegistrationError("${exception.message}")
                            }
                        }
                    }
                }
            }
    }

    private fun showRegistrationSuccess() {
        registrationStatus.value = RegistrationStatus.Success
    }

    private fun showRegistrationError(message: String) {
        registrationStatus.value = RegistrationStatus.Error(message)
    }
}