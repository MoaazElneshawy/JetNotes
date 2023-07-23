package com.moaazelneshawy.jetnotes.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.moaazelneshawy.jetnotes.models.ColorModel

@Composable
fun ColorsModels(onColorSelected: (ColorModel) -> Unit) {
    val colorsList = rememberSaveable {
        mutableStateOf(ColorModel.COLORS)
    }
    LazyColumn(content = {
        items(colorsList.value) {
            CColorIem(model = it, onClick = onColorSelected)
        }
    })
}