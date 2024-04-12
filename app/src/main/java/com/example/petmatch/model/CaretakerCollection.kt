package com.example.petmatch.model

data class CaretakerCollection(
    val id: Long,
    val name : String,
    val caretakers: List<Caretaker>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, Highlight}

object CaretakerRepo {
    fun getCaretakers() : List<CaretakerCollection> = caretakerCollections
    fun getCaretaker(caretakerId: Long) = caretakers.find { it.id == caretakerId }!!

    fun getRelated(@Suppress("UNUSED_PARAMETER") caretakerId: Long) = related
    fun getInspiredByCart() = inspiredByCart

}

private val highlights = CaretakerCollection(
    id = 1L,
    name = "Highlight caretaker",
    caretakers = caretakers.subList(0, 5),
    type = CollectionType.Highlight
)

private val popular = CaretakerCollection(
    id = 2L,
    name = "Popular on caretaker",
    caretakers = caretakers.subList(6, 9)
)

private val favorites = highlights.copy(
    id = 3L,
    name = "Favorite caretakers"
)

private val also = highlights.copy(
    id= 4L,
    name = "Customer also likes"
)

private val inspiredByCart = highlights.copy(
    id= 5L,
    name = "Inspired by your cart"
)

private val caretakerCollections = listOf(
    highlights,
    popular,
    favorites
)

private val related = listOf(
    also,
    popular
)



