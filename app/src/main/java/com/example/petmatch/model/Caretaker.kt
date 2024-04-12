package com.example.petmatch.model

import androidx.compose.runtime.Immutable

@Immutable
data class Caretaker(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val description: String = "",
    val service: List<Service> = emptyList(),
    val totalPrice: (List<Service>) -> Long
)

private val totalPrice: (List<Service>) -> Long = { services -> services.sumOf { it.price } }

val caretakers = listOf(
    Caretaker(
        id = 1L,
        name = "Ana",
        imageUrl = "https://images.pexels.com/photos/733872/pexels-photo-733872." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Committed person",
        service = listOf(
            Service(1L, walk.title, 100),
            Service(2L, bathe.title, 150),
            Service(3L, homeCare.title, 250)
        ),
        totalPrice = totalPrice
    ),
    Caretaker(
        id = 2L,
        name = "Juan",
        imageUrl = "https://images.pexels.com/photos/1681010/pexels-photo-1681010." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Excellent person",
        service = listOf(
            Service(1L, walk.title, 100),
            Service(2L, bathe.title, 100)
        ),
        totalPrice = totalPrice
    ),
    Caretaker(
        id = 3L,
        name = "Sofia",
        imageUrl = "https://images.pexels.com/photos/1065084/pexels-photo-1065084." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Social person",
        service = listOf(
            Service(1L, walk.title, 150),
            Service(2L, bathe.title, 100)
        ),
        totalPrice = totalPrice
    ),
    Caretaker(
        id = 4L,
        name = "Pedro",
        imageUrl = "https://images.pexels.com/photos/1212984/pexels-photo-1212984." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Happy person",
        service = listOf(
            Service(1L, walk.title, 100),
            Service(2L, bathe.title, 130)
        ),
        totalPrice = totalPrice
    ),
    Caretaker(
        id = 5L,
        name = "Luis",
        imageUrl = "https://images.pexels.com/photos/769745/pexels-photo-769745." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Responsible person",
        service = listOf(
            Service(1L, walk.title, 150),
            Service(2L, bathe.title, 150),
            Service(3L, homeCare.title, 200)
        ),
        totalPrice = totalPrice
    ),
    Caretaker(
        id = 6L,
        name = "Carlos",
        imageUrl = "https://images.pexels.com/photos/567459/pexels-photo-567459." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Amazing person",
        service = listOf(
            Service(1L, walk.title, 150),
            Service(2L, bathe.title, 150)
        ),
        totalPrice = totalPrice
    ),
    Caretaker(
        id = 7L,
        name = "Margarita",
        imageUrl = "https://images.pexels.com/photos/762020/pexels-photo-762020." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Excellent person",
        service = listOf(
            Service(1L, walk.title, 100),
            Service(2L, bathe.title, 100)
        ),
        totalPrice = totalPrice
    ),
    Caretaker(
        id = 8L,
        name = "Jose",
        imageUrl = "https://images.pexels.com/photos/428333/pexels-photo-428333." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Committed person",
        service = listOf(
            Service(1L, walk.title, 150),
            Service(2L, bathe.title, 250),
            Service(3L, homeCare.title, 250)
        ),
        totalPrice = totalPrice
    ),
    Caretaker(
        id = 9L,
        name = "Patricia",
        imageUrl = "https://images.pexels.com/photos/428321/pexels-photo-428321." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Amazing person",
        service = listOf(
            Service(1L, walk.title, 100),
            Service(2L, bathe.title, 100),
            Service(3L, homeCare.title, 250)
        ),
        totalPrice = totalPrice
    ),
    Caretaker(
        id = 10L,
        name = "Manuel",
        imageUrl = "https://images.pexels.com/photos/1205033/pexels-photo-1205033." +
                "jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        description = "Happy person",
        service = listOf(
            Service(1L, walk.title, 100),
            Service(2L, bathe.title, 150)
        ),
        totalPrice = totalPrice
    )
)