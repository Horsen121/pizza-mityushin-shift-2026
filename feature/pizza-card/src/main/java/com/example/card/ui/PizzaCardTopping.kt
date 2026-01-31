package com.example.card.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pizza.model.ui.ToppingUI
import com.example.pizza.utils.toNameRes
import com.example.theme.components.BodySmallText
import com.example.theme.components.LabelMediumText
import com.example.theme.elements.NetworkImage

@Composable
internal fun PizzaCardTopping(
    topping: ToppingUI,
    onToppingClick: (Int) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier
            .size(120.dp, 200.dp)
            .clickable(onClick = { onToppingClick(topping.type.toNameRes()) })
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            NetworkImage(
                topping.img,
                modifier = Modifier.size(110.dp)
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                BodySmallText(
                    topping.type.toNameRes(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                LabelMediumText(
                    "${topping.price} ₽",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}