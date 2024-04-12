package com.example.petmatch.view.ui_theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.petmatch.view.components.PetMatchSurface

@Composable
fun MyApp(content: @Composable () -> Unit) {
    PetMatchTheme {
        // A surface container using the 'background' color from the theme
        PetMatchSurface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}