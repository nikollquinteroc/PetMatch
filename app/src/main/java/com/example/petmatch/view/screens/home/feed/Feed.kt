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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petmatch.model.CaretakerCollection
import com.example.petmatch.model.CaretakerRepo
import com.example.petmatch.view.components.CaretakerCollection
import com.example.petmatch.view.components.PetMatchDivider
import com.example.petmatch.view.components.PetMatchSurface
import com.example.petmatch.view.screens.PetMatchTopAppBar
import com.example.petmatch.view.screens.home.HomeSections
import com.example.petmatch.view.screens.home.PetMatchBottomBar
import com.example.petmatch.view.ui_theme.PetMatchTheme

@Composable
fun Feed(
    onCaretakerClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val caretakerCollections = remember { CaretakerRepo.getCaretakers() }
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
            caretakerCollections = caretakerCollections,
            onCaretakerClick = onCaretakerClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun Feed(
    caretakerCollections: List<CaretakerCollection>,
    onCaretakerClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    PetMatchSurface(modifier = modifier.fillMaxSize()) {
        Box {
            CaretakerCollectionList(
                caretakerCollections = caretakerCollections,
                onCaretakerClick = onCaretakerClick
            )
            PetMatchTopAppBar()
        }
    }
}

@Composable
private fun CaretakerCollectionList(
    caretakerCollections: List<CaretakerCollection>,
    onCaretakerClick: (Long) -> Unit,
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
            itemsIndexed(caretakerCollections) {index, caretakerCollection ->
                if (index > 0) {
                    PetMatchDivider(thickness = 2.dp)
                }

                 CaretakerCollection(
                    caretakerCollection =caretakerCollection,
                    onCaretakerClick = onCaretakerClick,
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
            onCaretakerClick = {},
            onNavigateToRoute = {}
        )
    }
}





















