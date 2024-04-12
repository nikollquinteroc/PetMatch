package com.example.petmatch.model

data class Service(
    val id: Long,
    val title: String,
    val price: Long = 0
)

val walk = Service(
    id = 1L,
    title = "Walk"
)

val bathe = Service(
    id = 2L,
    title = "Bathe"
)

val homeCare = Service(
    id = 3L,
    title = "Home Care"
)

