package com.example.petmatch.view.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petmatch.R
import com.example.petmatch.model.data.SignUpUIEvent
import com.example.petmatch.model.data.SignUpViewModel
import com.example.petmatch.view.components.ButtonComponent
import com.example.petmatch.view.components.CheckboxComponent
import com.example.petmatch.view.components.ClickableLoginTextComponent
import com.example.petmatch.view.components.DividerTextComponent
import com.example.petmatch.view.components.GenderDropdown
import com.example.petmatch.view.components.HeadingTextComponent
import com.example.petmatch.view.components.MyTextFieldComponent
import com.example.petmatch.view.components.NormalTextComponent
import com.example.petmatch.view.components.PasswordTextFieldComponent
import com.example.petmatch.view.components.RoleDropdown
import com.example.petmatch.view.navigation.PostOfficeAppRouter
import com.example.petmatch.view.navigation.Screen
import com.example.petmatch.view.ui_theme.MyApp


@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(),
    onNavigateToLogin: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(40.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                NormalTextComponent(
                    value = stringResource(id = R.string.hello)
                )
                HeadingTextComponent(
                    value = stringResource(id = R.string.create_account)
                )
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signUpViewModel.onEvent(SignUpUIEvent.FirstNameChange(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.firstNameError
                )
                Spacer(
                    modifier = Modifier
                        .height(9.dp)
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signUpViewModel.onEvent(SignUpUIEvent.LastNameChange(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.lastNameError
                )
                Spacer(
                    modifier = Modifier
                        .height(9.dp)
                )
                GenderDropdown(
                    onGenderSelected = { gender ->
                        signUpViewModel.onEvent(SignUpUIEvent.GenderChange(gender))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.genderError
                )
                Spacer(modifier = Modifier.height(9.dp))
                RoleDropdown(
                    onRoleSelected = { role ->
                        signUpViewModel.onEvent(SignUpUIEvent.RoleChange(role))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.roleError
                )
                Spacer(
                    modifier = Modifier
                        .height(9.dp)
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.message),
                    onTextChanged = {
                        signUpViewModel.onEvent(SignUpUIEvent.EmailNameChange(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.emailError
                )
                Spacer(
                    modifier = Modifier
                        .height(9.dp)
                )
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.PasswordNameChange(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.passwordError
                )
                CheckboxComponent(
                    value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = {
                        PostOfficeAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    },
                    onCheckedChange = {
                        signUpViewModel.onEvent(SignUpUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )
                Spacer(modifier = Modifier.height(30.dp))
                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        signUpViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signUpViewModel.allValidationsPassed.value
                )
                DividerTextComponent()
                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = { onNavigateToLogin(it) }
                )
            }
        }
        // Mostrar un indicador de progreso si el registro está en progreso
        if (signUpViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    MyApp {
        SignUpScreen(onNavigateToLogin = {})
    }
}