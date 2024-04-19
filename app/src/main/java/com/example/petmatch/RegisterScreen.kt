package com.example.petmatch

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petmatch.view.components.CheckboxComponent
import com.example.petmatch.view.components.ClickableLoginTextComponent
import com.example.petmatch.view.components.DividerText
import com.example.petmatch.view.components.GenderDropdown
import com.example.petmatch.view.components.HeadingTextComponent
import com.example.petmatch.view.components.MyTextFieldComponent
import com.example.petmatch.view.components.PasswordFieldComponent
import com.example.petmatch.view.components.RegisterTextComponent
import com.example.petmatch.view.components.RoleDropdown
import com.example.petmatch.view.components.btnRegister
import com.example.petmatch.view.data.RegisterViewModel
import androidx.compose.ui.platform.LocalContext
import com.example.petmatch.view.data.UIEvent
import com.example.petmatch.view.ui_theme.MyApp

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel = viewModel()) {
    val context = LocalContext.current

    val isChecked = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            RegisterTextComponent(value = stringResource(id = R.string.hey))
            HeadingTextComponent(value = stringResource(id = R.string.create))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first),
                painterResource = painterResource(id = R.drawable.user),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.FirstNameChanged(it))
                },
                errorStatus = registerViewModel.registerUIState.value.firstNameError,
                maxCharacters = 30
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last),
                painterResource = painterResource(id = R.drawable.user),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.LastNameChanged(it))
                },
                errorStatus = registerViewModel.registerUIState.value.lastNameError,
                maxCharacters = 30
            )

            Spacer(modifier = Modifier.height(7.dp))
            GenderDropdown(
                onGenderSelected = { gender ->
                    registerViewModel.onEvent(UIEvent.GenderChanged(gender))
                },
                errorStatus = registerViewModel.registerUIState.value.genderError
            )

            Spacer(modifier = Modifier.height(5.dp))
            RoleDropdown(
                onRoleSelected = { role ->
                    registerViewModel.onEvent(UIEvent.RoleChanged(role))
                },
                errorStatus = registerViewModel.registerUIState.value.roleError
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.EmailChanged(it))
                },
                errorStatus = registerViewModel.registerUIState.value.emailError,
                maxCharacters = 40
            )

            PasswordFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.PasswordChanged(it))
                },
                errorStatus = registerViewModel.registerUIState.value.passwordError,
                maxCharacters = 30
            )

            Spacer(modifier = Modifier.height(4.dp))
            CheckboxComponent(
                isChecked = isChecked
            )

            Spacer(modifier = Modifier.height(30.dp))

            btnRegister(
                value = stringResource(id = R.string.register),
                onRegisterBtn = {
                    registerViewModel.onEvent(UIEvent.RegisterBtn)
                },
                isEnabled = isChecked.value && registerViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(15.dp))

            DividerText()

            ClickableLoginTextComponent(onTextSelected = {})

            registerViewModel.registrationStatus.value?.let { status ->
                when (status) {
                    is RegisterViewModel.RegistrationStatus.Success -> {

                    }
                    is RegisterViewModel.RegistrationStatus.Error -> {
                        Toast.makeText(context, status.message, Toast.LENGTH_SHORT).show()
                    }
                }
                registerViewModel.registrationStatus.value = null
            }
        }
    }
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    MyApp {
        RegisterScreen()
    }
}