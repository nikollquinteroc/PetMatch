package com.example.petmatch.view.navigation

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLifecycleOwner

private val LocalBackPressedDispatcher =
    staticCompositionLocalOf<OnBackPressedDispatcherOwner?> { null }

private class ComposableBackNavigationHandler(enabled: Boolean) : OnBackPressedCallback(enabled) {
    lateinit var onBackPressed: () -> Unit

    override fun handleOnBackPressed() {
        onBackPressed()
    }

}

@Composable
internal  fun ComposableHandler(
    enabled: Boolean = true,
    onBackedPressed: () -> Unit
){
    val dispatcher = (LocalBackPressedDispatcher.current ?:return).onBackPressedDispatcher

    val handler = remember { ComposableBackNavigationHandler(enabled) }

    DisposableEffect(dispatcher) {
        dispatcher.addCallback(handler)


        onDispose { handler.remove() }

    }

    LaunchedEffect(enabled) {
        handler.isEnabled = enabled
        handler.onBackPressed = onBackedPressed
    }
}


@Composable
internal fun SystemBackButtonHandler(onBackedPressed: () -> Unit){
    CompositionLocalProvider(
        LocalBackPressedDispatcher provides  LocalLifecycleOwner.current as ComponentActivity
    ) {
        ComposableHandler {
            onBackedPressed()
        }

    }
}