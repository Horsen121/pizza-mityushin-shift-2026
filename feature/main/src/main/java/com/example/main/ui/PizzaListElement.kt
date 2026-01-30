package com.example.main.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.main.R
import com.example.main.domain.entity.PizzaCatalogItem

@Composable
fun PizzaListElement(
    pizza: PizzaCatalogItem,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable(onClick = { onClick(pizza.id) })
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
            modifier = Modifier.size(140.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                pizza.name,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                pizza.description,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                stringResource(R.string.pizza_catalog_price, pizza.minPrice),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}