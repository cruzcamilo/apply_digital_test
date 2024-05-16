package com.applydigitaltest.ui.mainscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeBox(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    val swipeState = rememberSwipeToDismissBoxState()

    val alignment =  Alignment.CenterEnd
    val color = when (swipeState.dismissDirection) {
        SwipeToDismissBoxValue.EndToStart -> {
            Color.Red
        }

        SwipeToDismissBoxValue.StartToEnd -> Color.Transparent

        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }

    SwipeToDismissBox(
        modifier = modifier.animateContentSize(),
        state = swipeState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            SwipeBackground(alignment, color)
        }
    ) {
        content()
    }

    when (swipeState.currentValue) {
        SwipeToDismissBoxValue.EndToStart -> {
            LaunchedEffect(swipeState) {
                onDelete()
                swipeState.snapTo(SwipeToDismissBoxValue.Settled)
            }
        }

        SwipeToDismissBoxValue.StartToEnd -> {}
        SwipeToDismissBoxValue.Settled -> {}
    }
}

@Composable
private fun SwipeBackground(
    alignment: Alignment,
    color: Color
) {
    Box(
        contentAlignment = alignment,
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = "Deleted",
            color = Color.White
        )
    }
}
