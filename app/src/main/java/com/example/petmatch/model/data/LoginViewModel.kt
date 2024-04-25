package com.example.petmatch.model.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.petmatch.model.data.rules.LoginUIEvent
import com.example.petmatch.model.data.rules.LoginUIState
import com.example.petmatch.model.data.rules.Validator
import com.example.petmatch.view.navigation.PostOfficeAppRouter
import com.example.petmatch.view.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.mindrot.jbcrypt.BCrypt


class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent){
        when(event){
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )

            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
            else -> {

            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status



    }

    private fun login() {
        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        val db = FirebaseFirestore.getInstance()
        val usersRef = db.collection("users")
        usersRef.whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    // Obtiene la contraseña encriptada del documento del usuario
                    val hashedPassword = document.getString("password")

                    // Verifica si la contraseña encriptada coincide con la contraseña proporcionada por el usuario
                    if (hashedPassword != null && BCrypt.checkpw(password, hashedPassword)) {
                        // La contraseña coincide, inicia sesión con Firebase Authentication
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                loginInProgress.value = false
                                if (task.isSuccessful) {
                                    loginInProgress.value = false
                                    PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
                                } else {
                                    // El inicio de sesión falló
                                    // Aquí puedes manejar el error, por ejemplo, mostrando un mensaje de error al usuario
                                    Log.d(TAG, "signInWithEmailAndPassword:failure", task.exception)
                                }
                            }
                        return@addOnSuccessListener
                    }
                }
                // Maneja el caso donde no se encuentra ningún usuario con el correo electrónico proporcionado
                // Puedes mostrar un mensaje de error al usuario indicando que la dirección de correo electrónico o la contraseña son incorrectas
                Log.d(TAG, "No se encontró ningún usuario con este correo electrónico o la contraseña es incorrecta")
            }
            .addOnFailureListener { exception ->
                // Maneja el error al obtener los datos del usuario de Firestore
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }
}