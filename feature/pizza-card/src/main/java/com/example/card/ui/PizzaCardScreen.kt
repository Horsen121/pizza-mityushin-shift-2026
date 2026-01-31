package com.example.card.ui

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.card.R
import com.example.card.domain.entity.PizzaCardItem
import com.example.card.presentation.PizzaCardState
import com.example.card.presentation.PizzaCardViewModel
import com.example.card.presentation.toDescRes
import com.example.pizza.utils.PizzaSizes
import com.example.theme.components.BodyLargeText
import com.example.theme.components.BodyMediumText
import com.example.theme.components.LabelMediumText
import com.example.theme.components.TitleText
import com.example.theme.elements.FullScreenProgressIndicator
import com.example.theme.elements.NetworkImage

@Composable
fun PizzaCardScreen(
    onBackClick: () -> Unit,
    viewModel: PizzaCardViewModel
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    when(val currentState = state) {
        is PizzaCardState.Initial, PizzaCardState.Loading -> {
            FullScreenProgressIndicator()
        }
        is PizzaCardState.Content -> {
            PizzaCardScreenContent(
                currentState,
                onBackClick,
                { viewModel.onSizeSelect(it) },
                viewModel.getIngredients(state)
            )
        }
        is PizzaCardState.Error -> {
            Toast.makeText(context, currentState.message, Toast.LENGTH_SHORT).show()
            Log.e("TAG", "PizzaCardScreenContent: ${currentState.message}")
        }
    }
}

@Composable
private fun PizzaCardScreenContent(
    state: PizzaCardState.Content,
    onBackClick: () -> Unit,
    onSizeSelect: (Int) -> Unit,
    ingredients: String
) {
    val pizza = state.pizza
    var currentSize by remember { mutableStateOf(pizza.sizes.first().type) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                NavTitle(
                    text = R.string.pizza_card_title,
                    onBackClick = onBackClick
                )

                PizzaInfo(
                    pizza = pizza,
                    size = currentSize,
                    ingredients = ingredients
                )

                SegmentedSizesControl(
                    pizza.sizes,
                    pizza.sizes.indexOf(pizza.sizes.first { it.type == currentSize }),
                    onItemSelection = { index ->
                        currentSize = pizza.sizes[index].type
                        onSizeSelect(index)
                    }
                )

                LabelMediumText(R.string.pizza_card_add_elements)
            }
        }
        items(pizza.toppings, key = { it.type }) {
            PizzaCardTopping(it) { }
        }
    }
}

@Composable
fun NavTitle(
    @StringRes text: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(28.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(62.dp)
            .clickable(onClick = { onBackClick() })
    ) {
        Image(
            Icons.Default.ArrowBackIosNew, null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary)
        )
        TitleText(text)
    }
}

@Composable
fun PizzaInfo(
    pizza: PizzaCardItem,
    size: PizzaSizes,
    ingredients: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NetworkImage(
            pizza.img,
            modifier = Modifier.size(250.dp)
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TitleText(pizza.name)
        BodyMediumText(
            "${stringResource(size.toDescRes())}, " +
                    " ${stringResource(pizza.doughs.first().type.toDescRes())}"
        )
        BodyLargeText(ingredients)
    }
}