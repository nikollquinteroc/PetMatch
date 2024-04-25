package com.example.petmatch.view.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petmatch.R
import com.example.petmatch.view.components.HeadingTextComponent
import com.example.petmatch.view.components.MyTextFieldComponent
import com.example.petmatch.model.data.LoginViewModel
import com.example.petmatch.model.data.rules.LoginUIEvent
import com.example.petmatch.view.components.ButtonComponent
import com.example.petmatch.view.components.ClickableLoginTextComponent
import com.example.petmatch.view.components.DividerTextComponent
import com.example.petmatch.view.components.HeadingTextComponent
import com.example.petmatch.view.components.MyTextFieldComponent
import com.example.petmatch.view.components.NormalTextComponent
import com.example.petmatch.view.components.PasswordTextFieldComponent
import com.example.petmatch.view.components.UnderLinedTextComponent
import com.example.petmatch.view.navigation.PostOfficeAppRouter
import com.example.petmatch.view.navigation.Screen
import com.example.petmatch.view.navigation.SystemBackButtonHandler
import com.example.petmatch.view.ui_theme.MyApp


@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Login icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                )
                NormalTextComponent(
                    value = stringResource(id = R.string.login)
                )
                HeadingTextComponent(
                    value = stringResource(id = R.string.welcome)
                )
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )
                Spacer(
                    modifier = Modifier
                        .height(9.dp)
                )
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                ButtonComponent(
                    value = stringResource(id = R.string.login), onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )
                Spacer(
                    modifier = Modifier
                        .height(40.dp)
                )
                DividerTextComponent()
                ClickableLoginTextComponent(
                    tryingToLogin = false,
                    onTextSelected = {
                        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
                    }
                )
            }
        }
        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }
    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    MyApp {
        LoginScreen()
    }
}