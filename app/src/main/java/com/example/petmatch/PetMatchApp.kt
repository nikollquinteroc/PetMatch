package com.example.petmatch

import android.location.Location
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
import com.example.petmatch.view.screens.contact.InfoContact
import com.example.petmatch.view.screens.home.HomeSections
import com.example.petmatch.view.screens.home.addHomeGraph
import com.example.petmatch.view.screens.login.LoginScreen
import com.example.petmatch.view.screens.petdetail.PetDetail
import com.example.petmatch.view.screens.register.SignUpScreen
import com.example.petmatch.view.screens.register.termsAndConditions.TermsAndConditionsScreen


@Composable
fun PetMatchApp(lastKnownLocation: Location?) {
    val petMatchNavController = rememberPetMatchNavController()
    NavHost(
        navController = petMatchNavController.navController,
        startDestination = MainDestinations.LOGIN_ROUTE
    ) {
        petMatchNavGraph(
            onPetSelectFromFeedScreen = petMatchNavController::navigateToPetDetail,
            onPetSelectFromDetailScreen = petMatchNavController::navigateToPetDetail,
            onViewPetOnMapScreen = petMatchNavController::navigateToMapFromDetailScreen,
            onAdoptPet = petMatchNavController::navigateToAdoptPetFromDetailScreen,
            upPress = petMatchNavController::upPress,
            onNavigateToRoute = petMatchNavController::navigateToBottomBarRoute,
            onNavigateToRegister = petMatchNavController::navigateToRegister,
            onNavigateToLogin = petMatchNavController::navigateToLogin,
            lastKnownLocation = lastKnownLocation
        )
    }
}

private fun NavGraphBuilder.petMatchNavGraph(
    onPetSelectFromFeedScreen: (Long, NavBackStackEntry) -> Unit,
    onPetSelectFromDetailScreen: (Long, NavBackStackEntry) -> Unit,
    onViewPetOnMapScreen: (Long, NavBackStackEntry) -> Unit,
    onAdoptPet: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit,
    onNavigateToRegister: (String, NavBackStackEntry) -> Unit,
    onNavigateToLogin: (String, NavBackStackEntry) -> Unit,
    lastKnownLocation: Location?
) {
    composable(MainDestinations.LOGIN_ROUTE) { from ->
        LoginScreen(
            onLoginSuccess = {
                onNavigateToRoute(MainDestinations.HOME_ROUTE)
            },
            onNavigateToRegister = { route -> onNavigateToRegister(route, from) }
        )
    }
    composable(MainDestinations.REGISTER_ADOPTER_ROUTE) { from ->
        SignUpScreen(onNavigateToLogin = { route -> onNavigateToLogin(route, from) })
    }
    composable(MainDestinations.TERMS_AND_CONDITIONS) {
        TermsAndConditionsScreen()
    }
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ) {
        addHomeGraph(
            onPetSelectedFromFeedScreen = onPetSelectFromFeedScreen,
            onNavigateToRoute = onNavigateToRoute,
            lastKnownLocation = lastKnownLocation,
            upPress = upPress
        )
    }
    composable(
        route = "${MainDestinations.PET_DETAIL_ROUTE}/{${MainDestinations.PET_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.PET_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val petId = arguments.getLong(MainDestinations.PET_ID_KEY)
        PetDetail(
            petId = petId,
            from = backStackEntry,
            upPress = upPress,
            onViewPetOnMapScreen = onViewPetOnMapScreen,
            onAdoptPet = onAdoptPet,
            onPetSelectFromDetailScreen = { id -> onPetSelectFromDetailScreen(id, backStackEntry) }
        )
    }
    composable(route = MainDestinations.INFO_CONTACT_ROUTE) {
        InfoContact(upPress = upPress)
    }
}