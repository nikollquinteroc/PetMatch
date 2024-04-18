package com.example.petmatch.model

data class PetCollection(
    val id: Long,
    val name : String,
    val pets: List<Pet>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, Highlight}

object PetRepo {
    fun getPets() : List<PetCollection> = petCollections
    fun getPet(petId: Long) = pets.find { it.id == petId }!!
    fun getRelated(@Suppress("UNUSED_PARAMETER") petId: Long) = related
}

private val highlights = PetCollection(
    id = 1L,
    name = "Featured Pets",
    pets = pets.subList(0, 5),
    type = CollectionType.Highlight
)

private val popular = PetCollection(
    id = 2L,
    name = "Preferred by the Adopters",
    pets = pets.subList(6, 9)
)

private val favorites = highlights.copy(
    id = 3L,
    name = "Favorites of the Adopters"
)

private val also = highlights.copy(
    id= 4L,
    name = "Recommended by Adopters"
)

private val petCollections = listOf(
    highlights,
    popular,
    favorites
)

private val related = listOf(
    also,
    popular
)



