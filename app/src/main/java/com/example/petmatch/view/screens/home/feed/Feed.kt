package com.example.petmatch.view.screens.home.feed

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petmatch.model.PetCollection
import com.example.petmatch.model.PetRepo
import com.example.petmatch.view.components.PetCollection
import com.example.petmatch.view.components.PetMatchDivider
import com.example.petmatch.view.components.PetMatchSurface
import com.example.petmatch.view.components.PetMatchTopAppBar
import com.example.petmatch.view.screens.home.HomeSections
import com.example.petmatch.view.screens.home.PetMatchBottomBar
import com.example.petmatch.view.ui_theme.PetMatchTheme

@Composable
fun Feed(
    onPetClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = viewModel(factory = FeedViewModel.provideFactory())
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val petCollections = remember { PetRepo.getPets() }
    Scaffold(
        bottomBar = {
            PetMatchBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.FEED.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Feed(
            petCollections = petCollections,
            onPetClick = onPetClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun Feed(
    petCollections: List<PetCollection>,
    onPetClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    PetMatchSurface(modifier = modifier.fillMaxSize()) {
        Box {
            CaretakerCollectionList(
                petCollections = petCollections,
                onPetClick = onPetClick
            )
            PetMatchTopAppBar()
        }
    }
}

@Composable
private fun CaretakerCollectionList(
    petCollections: List<PetCollection>,
    onPetClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        LazyColumn {

            item {
                Spacer(
                    modifier = Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
                )
            }
            itemsIndexed(petCollections) { index, petCollection ->
                if (index > 0) {
                    PetMatchDivider(thickness = 2.dp)
                }

                PetCollection(
                    petCollection = petCollection,
                    onPetClick = onPetClick,
                    index = index
                )
            }
        }
    }
}

@Preview(name = "default")
@Preview(name = "dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "large font", fontScale = 2f)
@Composable
fun HomePreview() {
    PetMatchTheme {
        Feed(
            onPetClick = {},
            onNavigateToRoute = {}
        )
    }
}





















