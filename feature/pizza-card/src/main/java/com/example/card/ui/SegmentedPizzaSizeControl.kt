package com.example.card.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.pizza.model.ui.SizeUI
import com.example.pizza.utils.toNameRes
import com.example.theme.components.BodyMediumText

@Composable
fun SegmentedSizesControl(
    items: ArrayList<SizeUI>,
    selectedIndex: Int,
    onItemSelection: (Int) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        val maxWidth = this.maxWidth
        val itemWidth = maxWidth / items.size

        val indicatorOffset by animateDpAsState(
            targetValue = itemWidth * selectedIndex,
            label = "indicator animation"
        )

        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .width(itemWidth)
                .fillMaxHeight()
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(8.dp))
                .zIndex(0f)
        )

        Row(
            modifier = Modifier.fillMaxSize().zIndex(1f)
        ) {
            items.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .width(itemWidth)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onItemSelection(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    BodyMediumText(
                        text = item.type.toNameRes(),
                        color = if (index == selectedIndex) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onTertiary,
                    )
                }
            }
        }
    }
}