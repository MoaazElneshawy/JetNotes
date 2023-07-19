package com.moaazelneshawy.jetnotes.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moaazelneshawy.jetnotes.models.ColorModel

@Composable
fun CColorIem(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    model: ColorModel,
    onClick: (ColorModel) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onClick.invoke(model) }
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        CNoteColor(
            size = 40.dp,
            borderWidth = 1.dp,
            color = model.color,
            modifier = contentModifier.padding(end = 20.dp)
        )
        Text(text = model.colorName, fontSize = 13.sp)
    }


}

@Preview
@Composable
fun PreviewColorItem() {
    CColorIem(model = ColorModel(color = Color.LightGray.hashCode(), colorName = "Light Gray")) {}
}