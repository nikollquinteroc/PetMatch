package com.example.petmatch.view.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petmatch.model.data.LoginViewModel
import com.example.petmatch.view.components.PetMatchButton
import com.example.petmatch.view.components.PetMatchTopAppBar
import com.example.petmatch.view.navigation.MainDestinations
import com.example.petmatch.view.ui_theme.MyApp

@Composable
fun LoginScreen(
    onNavigateToRoute: (String) -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {

    Scaffold(
        topBar = { PetMatchTopAppBar() },
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        PetMatchButton(
            onClick = { onNavigateToRoute(MainDestinations.HOME_ROUTE) },
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(text = "Next")
        }
    }


    /*Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MyImageComponent(
                painter = painterResource(id = R.drawable.user)
            )
            HeadingTextComponent(value = stringResource(id = "Welcome Back"))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                onTextChanged = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.emailError
            )
            PasswordFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_lock),
                onTextChanged = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.passwordError
            )

            Spacer(modifier = Modifier.height(4.dp))

            UnderLinedTextComponent(value = stringResource(id = "Forgot your password"))

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(
                value = stringResource(id = "Login"),
                onButtonClicked = {
                    loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                },
                isEnabled = loginViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(15.dp))

            DividerText2()

            ClickableLoginTextComponent2(onTextSelected = {

            })


        }

        SystemBackButtonHandler {
            PostOfficeAppRouter.navigateTo(Screeen.SignUpScreeen)
        }


    }

    if (loginViewModel.loginInProgress.value) {
        CircularProgressIndicator()
    }


}

SystemBackButtonHandler {
    PosOfficeAppRouter.navigateTO(Screen.SignUpScreen)
}
*/

}


@Preview
@Composable
fun PreviewRegisterScreen() {
    MyApp {
        LoginScreen(onNavigateToRoute = {})
    }
}