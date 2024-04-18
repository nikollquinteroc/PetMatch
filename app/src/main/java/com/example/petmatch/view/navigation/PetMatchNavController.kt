package com.example.petmatch.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petmatch.model.Pet

/**
 * Destinations used in [PetMatchApp]
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val PET_DETAIL_ROUTE = "pet"
    const val INFO_CONTACT_ROUTE = "info_contact"
    const val MAP = "map"
    const val PET_ID_KEY = "petId"
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
            if (route == MainDestinations.INFO_CONTACT_ROUTE) {
                navController.navigate(route)
            } else {
                navController.navigate(route) {
                    launchSingleTop = true
                    restoreState = true

                    popUpTo(findStartDestination(navController.graph).id) {
                        saveState = true
                    }
                }
            }
        }
    }

    fun navigateToPetDetail(petId: Long, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.PET_DETAIL_ROUTE}/$petId")
        }
    }

    fun navigateToMap(petId: Long) {
        navController.navigate("${MainDestinations.MAP}/${petId}")
    }

}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED


private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}








