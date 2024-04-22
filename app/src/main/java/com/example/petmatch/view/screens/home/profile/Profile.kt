package com.example.petmatch.view.screens.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.petmatch.view.components.PetImage
import com.example.petmatch.view.components.ToolBar
import com.example.petmatch.view.components.Up
import com.example.petmatch.view.screens.home.HomeSections
import com.example.petmatch.view.screens.home.PetMatchBottomBar

@Composable
fun Profile(
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            PetMatchBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.PROFILE.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
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
            Image()
        }
    }
}

@Composable
fun Image(

) {


}