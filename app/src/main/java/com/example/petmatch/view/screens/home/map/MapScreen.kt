package com.example.petmatch.view.screens.home.map

import android.content.res.Configuration
import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.petmatch.R
import com.example.petmatch.model.Pet
import com.example.petmatch.model.PetRepo
import com.example.petmatch.view.components.ToolBar
import com.example.petmatch.view.components.Up
import com.example.petmatch.view.screens.home.HomeSections
import com.example.petmatch.view.screens.home.PetMatchBottomBar
import com.example.petmatch.view.ui_theme.MyApp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MyMap(
    pets: List<Pet>,
    onNavigateToRoute: (String) -> Unit,
    lastKnownLocation: Location?,
    upPress: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val properties = remember {
        MapProperties(
            mapType = MapType.NORMAL,
            isMyLocationEnabled = lastKnownLocation != null
        )
    }

    val defaultLocation = LatLng(-33.8523341, 151.2106085)
    val myLocationLatLng =
        lastKnownLocation?.let { LatLng(it.latitude, it.longitude) } ?: defaultLocation

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(myLocationLatLng, 10f)
    }

    Scaffold(
        bottomBar = {
            PetMatchBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.LOCATION.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        topBar = {
            ToolBar()
            Up(upPress = upPress)
        },
        modifier = modifier
    ) { paddingValues ->
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            properties = properties,
            cameraPositionState = cameraPositionState
        ) {

            pets.forEach { pet ->
                Marker(
                    state = MarkerState(position = pet.latLng),
                    title = pet.name,
                    snippet = pet.tag,
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.pets)
                )
            }
        }
    }
}

private suspend fun CameraPositionState.centerOnLocation(location: Location) {
    animate(
        update = CameraUpdateFactory.newLatLngZoom(
            LatLng(location.latitude, location.longitude),
            15f
        ),
    )
}

@Preview("default")
@Preview(name = "dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MyMapPreview() {
    MyApp {

    }
}