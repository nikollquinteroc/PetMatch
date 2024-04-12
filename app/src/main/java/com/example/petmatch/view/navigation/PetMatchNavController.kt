package com.example.petmatch.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Destinations used in [PetMatchApp]
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val CARE_TAKER_DETAIL_ROUTE = "caretaker"
    const val CARE_TAKER_ID_KEY = "caretakerId"
}

@Composable
fun rememberPetMatchNavController(
    navController: NavHostController = rememberNavController()
): PetMatchNavController = remember(navController){
    PetMatchNavController(navController)
}

class PetMatchNavController(
    val navController: NavHostController
) {

    private val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true

                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToCaretakerDetail(caretakerId: Long, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.CARE_TAKER_DETAIL_ROUTE}/$caretakerId")
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








