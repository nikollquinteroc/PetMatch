package com.example.petmatch.view.screens.contact

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.petmatch.view.components.ScreenInProgress
import com.example.petmatch.view.components.ToolBar
import com.example.petmatch.view.components.Up
import com.example.petmatch.view.ui_theme.MyApp


@Composable
fun InfoContact(
    upPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        ToolBar()
        Up(upPress)
        ScreenInProgress()
    }
}


@Preview(name = "default")
@Preview(name = "dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InfoContactPreview() {
    MyApp {
        InfoContact(upPress = {})
    }
}