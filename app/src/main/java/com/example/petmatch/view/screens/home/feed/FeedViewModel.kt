package com.example.petmatch.view.screens.home.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petmatch.model.CollectionType
import com.example.petmatch.view.screens.petdetail.Pet
import com.example.petmatch.model.PetCollection
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FeedViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<FeedUiState> =
        MutableStateFlow(FeedUiState(isLoading = true))
    val uiState: StateFlow<FeedUiState> = _uiState

    private val db = Firebase.firestore
    fun getPets() {
        db.collection("pets")
            .get()
            .addOnSuccessListener { result ->
                val pets = mutableListOf<Pet>()
                for (document in result.documents) {
                    document.toObject(Pet::class.java)?.let { pets.add(it) }
                }

                val highlights = getHighlights(pets)
                val popular = getPopular(pets)
                val latest = getRecentlyAdded(pets)

                _uiState.update {
                    it.copy(
                        petCollection = listOf(highlights, popular, latest),
                        isLoading = false
                    )
                }
            }
            .addOnFailureListener { error ->
                _uiState.update {
                    it.copy(
                        errorMessage = error.message
                            ?: "There was an error by getting the pets from the Firebase database",
                        isLoading = false
                    )
                }
            }
    }

    private fun getHighlights(pets: List<Pet>): PetCollection {
        return PetCollection(
            id = 1L,
            name = "Featured Pets",
            pets = pets.subList(0, 5),
            type = CollectionType.Highlight
        )
    }

    private fun getPopular(pets: List<Pet>): PetCollection {
        return PetCollection(
            id = 2L,
            name = "My Favorite pets",
            pets = pets.subList(6, 9)
        )
    }

    private fun getRecentlyAdded(pets: List<Pet>): PetCollection {
        return PetCollection(
            id = 3L,
            name = "Recently added pets",
            pets = pets.subList(pets.size - 6, pets.size - 1)
        )
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FeedViewModel() as T
            }
        }
    }
}