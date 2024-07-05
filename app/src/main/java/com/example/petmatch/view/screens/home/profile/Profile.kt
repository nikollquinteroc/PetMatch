package com.example.petmatch.view.screens.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petmatch.R
import com.example.petmatch.view.components.CircularImagePicker
import com.example.petmatch.view.components.GenderEditDropdown
import com.example.petmatch.view.components.MyTextFieldComponent
import com.example.petmatch.view.components.PetMatchSurface
import com.example.petmatch.view.screens.home.HomeSections
import com.example.petmatch.view.screens.home.PetMatchBottomBar
import com.example.petmatch.view.ui_theme.MyApp

@Composable
fun Profile(
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        bottomBar = {
            PetMatchBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.PROFILE.route,
                navigateToRoute = onNavigateToRoute)
        },
        modifier = modifier
    ) { paddingValues ->
        PetMatchSurface(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                CircularImagePicker()
                Spacer(modifier = Modifier.height(20.dp))
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.first),
                    painterResource = painterResource(id = R.drawable.profile),
                    initialValue = "Nikoll"
                )
                Spacer(modifier = Modifier.height(10.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.last),
                    painterResource = painterResource(id = R.drawable.profile),
                    initialValue = "Quintero"
                )
                Spacer(modifier = Modifier.height(10.dp))
                GenderEditDropdown()
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    MyApp {
        Profile(onNavigateToRoute = {})
    }
}