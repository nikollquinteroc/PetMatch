package com.example.petmatch.model.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.petmatch.model.data.rules.Validator
import com.example.petmatch.view.navigation.PostOfficeAppRouter
import com.example.petmatch.view.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.firestore.FirebaseFirestore
import org.mindrot.jbcrypt.BCrypt


class SignUpViewModel : ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)


    fun onEvent(event: SignUpUIEvent) {

        when (event) {
            is SignUpUIEvent.FirstNameChange -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()

            }
            is SignUpUIEvent.LastNameChange -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()

            }
            is SignUpUIEvent.EmailNameChange -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }
            is SignUpUIEvent.PasswordNameChange -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }
            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp()
            }
            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )

            }
            is SignUpUIEvent.GenderChange -> {
                registrationUIState.value = registrationUIState.value.copy(gender = event.gender)

            }

            is SignUpUIEvent.RoleChange -> {
                registrationUIState.value = registrationUIState.value.copy(role = event.role)

            }

        }
        validateDataWithRules()
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }





    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val genderResult = Validator.validateGender(
            cboGender = registrationUIState.value.gender
        )
        val roleResult = Validator.validateRole(
            cboRol = registrationUIState.value.role
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "EmailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG,"genderResult= $genderResult")
        Log.d(TAG,"roleResult= $roleResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            genderError = genderResult.status,
            roleError = roleResult.status,
            privacyPolicyError = privacyPolicyResult.status

        )


        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && genderResult.status
                && roleResult.status && privacyPolicyResult.status
    }


    private fun printState(){
        Log.d(TAG, "Insede_printState")
        Log.d(TAG, registrationUIState.value.toString())

    }



    private fun createUserInFirebase(email: String, password: String) {
        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                signUpInProgress.value = false
                if (task.isSuccessful) {
                    // Encriptar la contraseña antes de guardarla
                    val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())

                    // Crear un nuevo documento en Cloud Firestore para el usuario registrado
                    val newUser = hashMapOf(
                        "firstName" to registrationUIState.value.firstName,
                        "lastName" to registrationUIState.value.lastName,
                        "email" to email,
                        "password" to hashedPassword, // Guardar la contraseña encriptada
                        "gender" to registrationUIState.value.gender,
                        "role" to registrationUIState.value.role,
                        "img_url" to "",
                        "pets" to FirebaseFirestore.getInstance().collection("pets").document()
                    )

                    val db = FirebaseFirestore.getInstance()
                    db.collection("users")
                        .document(FirebaseAuth.getInstance().currentUser?.uid ?: "")
                        .set(newUser)
                        .addOnSuccessListener {
                            // Navegar a la pantalla de inicio después de registrar y guardar datos en Firestore
                            PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
                        }
                        .addOnFailureListener { e ->
                            // Manejar el error al guardar en Firestore
                            Log.w(TAG, "Error writing document", e)
                        }
                }
            }
            .addOnFailureListener { exception ->
                // Manejar el error en la creación de usuario
                Log.d(TAG, "Exception= ${exception.message}")
                Log.d(TAG, "Exception= ${exception.localizedMessage}")
            }
    }





    fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign out success")
                // Reiniciar el estado del SignUpViewModel
                registrationUIState.value = RegistrationUIState()
                // Navegar a SignUpScreen
                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }





}