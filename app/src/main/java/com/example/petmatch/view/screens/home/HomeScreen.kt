package com.example.petmatch.view.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petmatch.R
import com.example.petmatch.model.data.SignUpViewModel
import com.example.petmatch.view.components.ButtonComponent
import com.example.petmatch.view.components.HeadingTextComponent

@Composable
fun HomeScreen(signUpViewModel: SignUpViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            HeadingTextComponent(value = stringResource(R.string.home))

            ButtonComponent(
                value = stringResource(id = R.string.logout), onButtonClicked = {
                    signUpViewModel.logout()
                },
                isEnabled = true
            )

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}


