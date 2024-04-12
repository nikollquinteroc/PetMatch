package com.example.petmatch.view.screens.caretakerdetail

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import com.example.petmatch.R
import com.example.petmatch.model.Caretaker
import com.example.petmatch.model.CaretakerCollection
import com.example.petmatch.model.CaretakerRepo
import com.example.petmatch.view.components.CaretakerCollection
import com.example.petmatch.view.components.CaretakerImage
import com.example.petmatch.view.components.PetMatchButton
import com.example.petmatch.view.components.PetMatchDivider
import com.example.petmatch.view.components.PetMatchSurface
import com.example.petmatch.view.ui_theme.PetMatchTheme
import com.example.petmatch.view.ui_theme.md_theme_light_shadow
import com.example.petmatch.view.utils.mirroringBackIcon
import kotlin.math.max
import kotlin.math.min


private val BottomBarHeight = 56.dp
private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun CaretakerDetail(
    caretakerId: Long,
    upPress: () -> Unit
) {
    val caretaker = remember(caretakerId) { CaretakerRepo.getCaretaker(caretakerId) }
    val related = remember(caretakerId) { CaretakerRepo.getRelated(caretakerId) }
    var selectedCount by remember { mutableIntStateOf(0) }

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body(
            related = related,
            scroll = scroll,
            caretaker = caretaker,
            onSelectedCountChanged = { count ->
                selectedCount = count
            }
        )
        Title(caretaker = caretaker) { scroll.value}
        Image(imageUrl = caretaker.imageUrl) { scroll.value }
        Up(upPress)
        CartBottomBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(
                Brush
                    .horizontalGradient(
                        mutableListOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.tertiaryContainer
                        )
                    )
            )
    )
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = md_theme_light_shadow.copy(alpha = 0.3f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = mirroringBackIcon(),
            tint = MaterialTheme.colorScheme.primaryContainer,
            contentDescription = stringResource(id = R.string.label_back)
        )
    }
}

@Composable
private fun Body(
    related: List<CaretakerCollection>,
    scroll: ScrollState,
    caretaker: Caretaker,
    onSelectedCountChanged: (Int) -> Unit,

) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(modifier = Modifier.height(GradientScroll))
            PetMatchSurface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(modifier = Modifier.height(ImageOverlap))
                    Spacer(modifier = Modifier.height(TitleHeight))

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.detail_header),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = HzPadding
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    var seeMore by remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(id = R.string.detail_placeholder),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = if (seeMore) 5 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis,
                        modifier = HzPadding
                    )
                    val textButton = if (seeMore) {
                        stringResource(id = R.string.see_more)
                    } else {
                        stringResource(id = R.string.see_less)
                    }
                    Text(
                        text = textButton,
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .heightIn(20.dp)
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                            .clickable {
                                seeMore = !seeMore
                            }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = stringResource(id = R.string.services),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = HzPadding
                    )
                    Spacer(modifier =  Modifier.height(4.dp))
                    ChecklistServices(
                        caretaker = caretaker,
                        onSelectedCountChanged = onSelectedCountChanged
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    PetMatchDivider()

                    related.forEach { caretakerCollection ->
                        key(caretakerCollection.id) {
                            CaretakerCollection(
                                caretakerCollection = caretakerCollection,
                                onCaretakerClick = { },
                                highlight = false
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(bottom = BottomBarHeight)
                            .navigationBarsPadding()
                            .height(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ChecklistServices(
    caretaker: Caretaker,
    onSelectedCountChanged: (Int) -> Unit
) {
    var selectedCount by remember { mutableIntStateOf(0) }
    Column {
        caretaker.service.forEach {service ->
            TextChecklistItem(
                serviceTitle = service.title,
                onCheckedChange = {isChecked ->
                    selectedCount += if (isChecked) 1 else -1
                    onSelectedCountChanged(selectedCount)
                },
                servicePrice = service.price
            )
        }
    }
}

@Composable
fun TextChecklistItem(
    serviceTitle: String,
    servicePrice: Long,
    onCheckedChange: (Boolean) -> Unit
) {
    var checkedState by remember { mutableStateOf(false) }
    Row(
        modifier = HzPadding.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { isChecked ->
                checkedState = isChecked
                onCheckedChange(isChecked)
            },
        )
        Text(
            text = serviceTitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = HzPadding
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "$ $servicePrice",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = HzPadding
            )
        }
    }
}

@Composable
private fun Title(caretaker: Caretaker, scrollProvider: () -> Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx()}
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx()}

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .offset {
                val scroll = scrollProvider()
                val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = caretaker.name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = HzPadding
        )
        Text(
            text = caretaker.description,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = HzPadding
        )
        Spacer(modifier = Modifier.height(8.dp))
        PetMatchDivider()
    }

}

@Composable
private fun Image(
    imageUrl: String,
    scrollProvider: () -> Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.statusBarsPadding()
    ) {
        CaretakerImage(
            imageUrl = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }

}


@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) {measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            start = (constraints.maxWidth - imageWidth) / 2,
            stop = constraints.maxWidth - imageWidth,
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ){
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}


@Composable
private fun CartBottomBar(modifier: Modifier = Modifier) {
    PetMatchSurface(modifier = modifier) {
        Column {
            PetMatchDivider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .navigationBarsPadding()
                    .then(HzPadding)
                    .heightIn(min = BottomBarHeight)
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                PetMatchButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.add_to_cart),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview(name = "default")
@Preview(name = "dark them", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CaretakerDetailPreview() {
    PetMatchTheme {
        CaretakerDetail(
            caretakerId = 1L,
            upPress = { }
        )
    }
}