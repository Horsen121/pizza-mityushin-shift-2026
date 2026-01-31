package com.example.theme.elements

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Loop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NetworkImage(
    imgPath: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://shift-intensive.ru/api/$imgPath")
            .crossfade(true)
            .build(),
        placeholder = rememberVectorPainter(Icons.Default.Loop),
        error = rememberVectorPainter(Icons.Default.Error),
        onError = { Log.e("TAG", "NetworkImage: ${it.result.throwable})") },
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}