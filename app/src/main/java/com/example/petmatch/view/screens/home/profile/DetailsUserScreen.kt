package com.example.petmatch.view.screens.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petmatch.view.components.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.petmatch.R
import com.example.petmatch.model.data.ProfileViewModel
import com.example.petmatch.view.screens.home.HomeSections
import com.example.petmatch.view.screens.home.PetMatchBottomBar

@Composable
fun DetailsUserScreen(
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit,
    profileViewModel: ProfileViewModel
) {
    val userState = profileViewModel.user

    val imageUrl = profileViewModel.imageUrl.value

    val isButtonEnabled = true

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Scaffold(
            bottomBar = {
                PetMatchBottomBar(
                    tabs = HomeSections.entries.toTypedArray(),
                    currentRoute = HomeSections.PROFILE.route,
                    navigateToRoute = onNavigateToRoute
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ToolBar()
                Up(upPress)

                CircularImagePicker(
                    imageUrl = imageUrl,
                    onImageSelected = { imageUrl -> },
                    profileViewModel = profileViewModel
                )

                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.first),
                    painterResource = painterResource(id = R.drawable.user),
                    initialValue = userState.value.firstName ?: "",
                    onValueChange = { text ->
                        val updatedUser = userState.value.copy(firstName = text)
                        profileViewModel.updateUserProfile(updatedUser)
                    },
                    userState = userState.value
                )

                Spacer(modifier = Modifier.height(10.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.last),
                    painterResource = painterResource(id = R.drawable.user),
                    initialValue = userState.value.lastName ?: "",
                    onValueChange = { text ->
                        val updatedUser = userState.value.copy(lastName = text)
                        profileViewModel.updateUserProfile(updatedUser)
                    },
                    userState = userState.value
                )

                Spacer(modifier = Modifier.height(10.dp))

                GenderEditDropdown(
                    selectedGender = userState.value.gender ?: "",
                    onGenderSelected = { gender ->
                        val updatedUser = userState.value.copy(gender = gender)
                        profileViewModel.updateUserProfile(updatedUser)
                    }
                )

                Spacer(modifier = Modifier.height(30.dp))

                btnUpdate(
                    value = stringResource(id = R.string.update),
                    onUpdateBtn = {
                        profileViewModel.updateUserProfile(userState.value)
                    },
                    isEnabled = isButtonEnabled
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewRDetailsUserScreen() {
    val profileViewModel = remember { ProfileViewModel() }
    DetailsUserScreen(
        upPress = {},
        onNavigateToRoute = { route -> },
        profileViewModel = profileViewModel
    )
}
