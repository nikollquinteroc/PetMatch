package com.example.petmatch.view.screens.home.feed

import com.example.petmatch.model.PetCollection

data class FeedUiState(
    val petCollection: List<PetCollection> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
