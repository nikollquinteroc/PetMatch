package com.example.petmatch.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource
import com.example.petmatch.R
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.PropertyName

@Immutable
data class Pet (
    val id: Long,
    val name: String,
    @PropertyName("image_url")
    val imageUrl: String,
    val tag: String = "",
    val description: String = "",
    val latLng: LatLng
)

val pets = listOf(
    Pet(
        id = 1L,
        name = "Lolita",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1707177763.jpg",
        tag = "Loyal pet",
        latLng = LatLng(3.338658, -76.5494643)

    ),
    Pet(
        id = 2L,
        name = "Simba",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1651693211.jpg",
        tag = "Playful pet",
        latLng = LatLng(3.3950644, -76.525664)
    ),
    Pet(
        id = 3L,
        name = "Rayo",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1651691628.jpg",
        tag = "Faithful pet",
        latLng = LatLng(3.3697814, -76.53704)
    ),
    Pet(
        id = 4L,
        name = "Toby",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1651688168.jpg",
        tag = "Energy mascot",
        latLng = LatLng(3.4100706, -76.5621871)
    ),
    Pet(
        id = 5L,
        name = "Chispa",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1651689459.jpg",
        tag = "Pet friendly",
        latLng = LatLng(3.4091105, -76.5797172)
    ),
    Pet(
        id = 6L,
        name = "Luna",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1651275948.jpg",
        tag = "Protective pet",
        latLng = LatLng(3.4487506, -76.541014)
    ),
    Pet(
        id = 7L,
        name = "Coco",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1651692429.jpg",
        tag = "Smart pet",
        latLng = LatLng(4.7986924, -75.29124)
    ),
    Pet(
        id = 8L,
        name = "Max",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1651689262.jpg",
        tag = "Obedient pet",
        latLng = LatLng(3.4744071, -76.4833757)
    ),
    Pet(
        id = 9L,
        name = "Oreo",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1651692356.jpg",
        tag = "Loving pet",
        latLng = LatLng(3.4896294, -76.5432513)
    ),
    Pet(
        id = 10L,
        name = "Zoe",
        imageUrl = "https://www.fundacionanimalove.org/images/productos/1_1651682963.jpg",
        tag = "Agile pet",
        latLng = LatLng(3.4691669, -76.4957139)
    )
)