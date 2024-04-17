package com.example.petmatch.view.screens

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
import com.example.petmatch.view.components.ClickableLoginTextComponent2
import com.example.petmatch.view.components.DividerText2
import com.example.petmatch.view.components.HeadingTextComponent
import com.example.petmatch.view.components.MyImageComponent
import com.example.petmatch.view.components.MyTextFieldComponent
import com.example.petmatch.view.components.PasswordFieldComponent
import com.example.petmatch.view.data.LoginViewModel
import com.example.petmatch.view.data.rules.LoginUIEvent

// @Composable
// fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
//
// Box(
// modifier = Modifier.fillMaxSize(),
// contentAlignment = Alignment.Center
// )
//
// Surface (
// modifier = Modifier
// .fillMaxSize()
// .background(Color.Transparent)
// .padding(28.dp)
//
// ) {
// Column(
// modifier = Modifier
// .fillMaxSize()) {
//
// MyImageComponent(
// painter = painterResource(id=R.drawable.user)
// )
//
// //NormalTextComponent(value = stringResource(id = "Login"))
//
// HeadingTextComponent(value = stringResource(id = "Welcome Back"))
//
// Spacer(modifier = Modifier.height(20.dp))
//
// MyTextFieldComponent(
// labelValue = stringResource(id = R.string.email),
// painterResource = painterResource(id = R.drawable.email),
// onTextChanged = {
// loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
// },
// errorStatus = loginViewModel.loginUIState.value.emailError
// )
// PasswordFieldComponent(
// labelValue = stringResource(id = R.string.password),
// painterResource = painterResource(id = R.drawable.ic_lock),
// onTextChanged = {
// loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
// },
// errorStatus = loginViewModel.loginUIState.value.passwordError
// )
//
// Spacer(modifier = Modifier.height(4.dp))
//
// UnderLinedTextComponent(value = stringResource(id = "Forgot your password" ))
//
// Spacer(modifier = Modifier.height(30.dp))
//
// ButtonComponent(
// value = stringResource(id = "Login"),
// onButtonClicked = {
// loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
// },
// isEnabled = loginViewModel.allValidationsPassed.value
// )
//
// Spacer(modifier = Modifier.height(15.dp))
//
// DividerText2()
//
// ClickableLoginTextComponent2(onTextSelected = {
//
// })
//
//
//
// }
//
// SystemBackButtonHandler {
// PostOfficeAppRouter.navigateTo(Screeen.SignUpScreeen)
// }
//
//
//
// }
//
// if (loginViewModel.loginInProgress.value) {
// CircularProgressIndicator()
// }
//
//
// }
//
// SystemBackButtonHandler {
// PosOfficeAppRouter.navigateTO(Screen.SignUpScreen)
// }
//
//
// }
//
//
// @Preview
// @Composable
// fun PreviewRegisterScreen() {
// LoginScreen()
//
// }