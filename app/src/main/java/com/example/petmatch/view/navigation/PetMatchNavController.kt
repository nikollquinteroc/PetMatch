package com.example.petmatch.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petmatch.view.screens.home.HomeSections

/**
 * Destinations used in [PetMatchApp]
 */
object MainDestinations {
    const val LOGIN_ROUTE = "login"
    const val REGISTER_ADOPTER_ROUTE = "register_adopter"
    const val TERMS_AND_CONDITIONS = "terms_and_conditions"
    const val HOME_ROUTE = "home"
    const val PET_DETAIL_ROUTE = "pet"
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
    const val INFO_CONTACT_ROUTE = "info_contact"
    const val PET_ID_KEY = "petId"
}

@Composable
fun rememberPetMatchNavController(
    navController: NavHostController = rememberNavController()
): PetMatchNavController = remember(navController) {
    PetMatchNavController(navController)
}

class PetMatchNavController(
    val navController: NavHostController
) {

    private val currentRoute: String?
        get() = navController.currentDestination?.route

    fun

            upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                // Pop up backstack to the first destination and save state. This makes going back
                // to the start destination when pressing back in any other bottom tab.
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToPetDetail(petId: Long, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.PET_DETAIL_ROUTE}/$petId")
        }
    }

    fun navigateToMapFromDetailScreen(petId: Long, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(HomeSections.MAP.route)
        }
    }

    fun navigateToAdoptPetFromDetailScreen(petId: Long, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(MainDestinations.INFO_CONTACT_ROUTE)
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED


private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}








