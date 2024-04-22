package com.example.petmatch.view.screens.home.profile

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petmatch.view.components.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.petmatch.R
import com.example.petmatch.model.data.ProfileViewModel


@Composable
fun DetailsUserScreen(viewModel: ProfileViewModel) {
    // Cargar los datos del usuario al inicializar la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadUserProfile()
    }

    // Obtener los datos del usuario del ViewModel
    val user = viewModel.user.value


    // Mostrar los datos del usuario en los campos de edición
    CircularImagePicker() // Aquí muestra la imagen del usuario
    Spacer(modifier = Modifier.height(20.dp))
    MyTextFieldComponent(
        labelValue = stringResource(id = R.string.first),
        painterResource = painterResource(id = R.drawable.user),
        initialValue = user.firstName ?: "",
        onValueChange = { newValue ->
            viewModel.user.value = user.copy(firstName = newValue)
        }
    )
    Spacer(modifier = Modifier.height(10.dp))

    MyTextFieldComponent(
        labelValue = stringResource(id = R.string.last),
        painterResource = painterResource(id = R.drawable.user),
        initialValue = user.lastName ?: "",
        onValueChange = { newValue ->
            viewModel.user.value = user.copy(lastName = newValue)
        }
    )
    Spacer(modifier = Modifier.height(10.dp))
    GenderEditDropdown(
        selectedGender  = user.gender ?: "",
        onGenderSelected = { newValue ->
            viewModel.user.value = user.copy(gender = newValue)
        }
    )
}

@Preview
@Composable
fun PreviewRDetailsUserScreen() {
    val viewModel = remember { ProfileViewModel() }
    DetailsUserScreen(viewModel)
}
