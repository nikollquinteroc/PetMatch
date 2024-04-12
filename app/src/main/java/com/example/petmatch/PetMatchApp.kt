package com.example.petmatch

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.petmatch.view.navigation.MainDestinations
import com.example.petmatch.view.navigation.rememberPetMatchNavController
import com.example.petmatch.view.screens.home.HomeSections
import com.example.petmatch.view.screens.home.addHomeGraph
import com.example.petmatch.view.screens.caretakerdetail.CaretakerDetail


@Composable
fun PetMatchApp() {
    val petMatchNavController = rememberPetMatchNavController()
    NavHost(
        navController = petMatchNavController.navController,
        startDestination = MainDestinations.HOME_ROUTE
    ) {
        petMatchNavGraph(
            onCaretakerSelect = petMatchNavController::navigateToCaretakerDetail,
            upPress = petMatchNavController::upPress,
            onNavigateToRoute = petMatchNavController::navigateToBottomBarRoute
        )
    }
}

private fun NavGraphBuilder.petMatchNavGraph(
    onCaretakerSelect: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ) {
        addHomeGraph(
            onCaretakerSelected = onCaretakerSelect,
            onNavigateToRoute = onNavigateToRoute
        )
    }
    composable(
        route = "${MainDestinations.CARE_TAKER_DETAIL_ROUTE}/{${MainDestinations.CARE_TAKER_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.CARE_TAKER_ID_KEY) {
            type = NavType.LongType }
        )
    ){backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val caretakerId = arguments.getLong(MainDestinations.CARE_TAKER_ID_KEY)
        CaretakerDetail(caretakerId, upPress)
    }
}