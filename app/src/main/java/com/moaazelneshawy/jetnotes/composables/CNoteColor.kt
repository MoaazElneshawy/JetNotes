package com.moaazelneshawy.jetnotes.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CNoteColor(modifier: Modifier = Modifier, size: Dp, borderWidth: Dp, color: Int) {
    Box(
        modifier = modifier
            .padding(vertical = 10.dp)
            .size(size)
            .clip(CircleShape)
            .background(color = Color(color))
            .border(border = BorderStroke(borderWidth, Color.Black), shape = CircleShape)
    )
}
