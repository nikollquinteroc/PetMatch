package com.example.petmatch.view.screens.home.map

import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient

class MapViewModel : ViewModel() {

    val locationState: MutableState<Location?> = mutableStateOf(null)

    fun getDeviceLocation(fusedLocationProviderClient: FusedLocationProviderClient) {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    locationState.value = task.result
                }
            }
        } catch (_: SecurityException) {
        }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MapViewModel() as T
            }
        }
    }
}