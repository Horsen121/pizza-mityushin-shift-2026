package com.example.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.main.R
import com.example.main.domain.entity.PizzaCatalogItem
import com.example.theme.components.BodySmallText
import com.example.theme.components.LabelMediumText
import com.example.theme.elements.NetworkImage

@Composable
internal fun PizzaListElement(
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
        NetworkImage(
            pizza.img,
            modifier = Modifier.size(140.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            LabelMediumText(pizza.name)
            BodySmallText(pizza.description)
            LabelMediumText(stringResource(R.string.pizza_catalog_price, pizza.minPrice))
        }
    }
}