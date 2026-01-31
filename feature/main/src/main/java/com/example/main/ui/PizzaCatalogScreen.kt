package com.example.main.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.main.R
import com.example.main.presentation.PizzaCatalogState
import com.example.main.presentation.PizzaCatalogViewModel
import com.example.theme.components.TitleText
import com.example.theme.elements.FullScreenProgressIndicator
import org.koin.androidx.compose.koinViewModel

@Composable
fun PizzaCatalogScreen(
    onItemClick: (Long) -> Unit
) {
    val viewModel: PizzaCatalogViewModel = koinViewModel()
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Crossfade(targetState = state) { currentState ->
        when(currentState) {
            is PizzaCatalogState.Initial, PizzaCatalogState.Loading -> {
                FullScreenProgressIndicator()
            }
            is PizzaCatalogState.Content -> {
                PizzaCatalogScreenContent(
                    currentState,
                    onItemClick
                )
            }
            is PizzaCatalogState.Error -> {
                Toast.makeText(context, currentState.message, Toast.LENGTH_SHORT).show()
                Log.e("TAG", "PizzaCatalogScreen: ${currentState.message}")
            }
        }
    }
}

@Composable
private fun PizzaCatalogScreenContent(
    state: PizzaCatalogState.Content,
    onItemClick: (Long) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
        ) {
            TitleText(R.string.pizza_catalog_title)
        }

        LazyColumn {
            items(state.pizzaCatalog, key = { it.id }) {
                PizzaListElement(
                    it,
                    onItemClick
                )
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}