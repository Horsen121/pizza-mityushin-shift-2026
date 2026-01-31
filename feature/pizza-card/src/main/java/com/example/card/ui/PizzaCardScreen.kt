package com.example.card.ui

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.card.R
import com.example.card.presentation.PizzaCardState
import com.example.card.presentation.PizzaCardViewModel
import com.example.card.presentation.toDescRes

@Composable
fun PizzaCardScreen(
    onBackClick: () -> Unit,
    viewModel: PizzaCardViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PizzaCardScreenContent(
        state,
        onBackClick,
        { viewModel.onSizeSelect(it) },
        viewModel.getIngredients(state)
    )
}

@Composable
private fun PizzaCardScreenContent(
    state: PizzaCardState,
    onBackClick: () -> Unit,
    onSizeSelect: (Int) -> Unit,
    ingredients: String
) {
    val context = LocalContext.current
    LaunchedEffect(state) {
        when(state) {
            is PizzaCardState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                Log.e("TAG", "PizzaCardScreenContent: ${state.message}")
            }
            else -> {}
        }
    }

    if(state is PizzaCardState.Content) {
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

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(28.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(62.dp)
                            .clickable(onClick = { onBackClick() })
                    ) {
                        Image(
                            Icons.Default.ArrowBackIosNew, null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary)
                        )
                        Text(
                            stringResource(R.string.pizza_card_title),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://shift-intensive.ru/api/${pizza.img}")
                                .crossfade(true)
                                .build(),
                            placeholder = rememberVectorPainter(Icons.Default.Loop),
                            error = rememberVectorPainter(Icons.Default.Error),
                            onError = { Log.e("TAG", "PizzaListElement: ${it.result.throwable})") },
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(250.dp)
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            pizza.name,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            "${stringResource(currentSize.toDescRes())},  ${stringResource(pizza.doughs.first().type.toDescRes())}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            ingredients,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    SegmentedSizesControl(
                        pizza.sizes,
                        pizza.sizes.indexOf(pizza.sizes.first { it.type == currentSize }),
                        onItemSelection = { index ->
                            currentSize = pizza.sizes[index].type
                            onSizeSelect(index)
                        }
                    )

                    Text(
                        stringResource(R.string.pizza_card_add_elements),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
            items(pizza.toppings, key = { it.type }) {
                PizzaCardTopping(it) { }
            }
        }
    }
}