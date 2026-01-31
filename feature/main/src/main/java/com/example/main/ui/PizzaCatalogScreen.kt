package com.example.main.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.main.R
import com.example.main.presentation.PizzaCatalogState
import com.example.main.presentation.PizzaCatalogViewModel

@Composable
fun PizzaCatalogScreen(
    onItemClick: (Long) -> Unit,
    viewModel: PizzaCatalogViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PizzaCatalogScreenContent(
        state,
        onItemClick
    )
}

@Composable
fun PizzaCatalogScreenContent(
    state: PizzaCatalogState,
    onItemClick: (Long) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(state) {
        when(state) {
            is PizzaCatalogState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                Log.e("TAG", "PizzaCatalogScreenContent: ${state.message}")
            }
            else -> {}
        }
    }

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
            Text(
                stringResource(R.string.pizza_catalog_title),
                style = MaterialTheme.typography.titleLarge
            )
        }

        LazyColumn {
            if (state is PizzaCatalogState.Content)
                items(state.pizzaCatalog) {
                    PizzaListElement(
                        it,
                        onItemClick
                    )
                    Spacer(Modifier.height(20.dp))
                }
        }
    }
}