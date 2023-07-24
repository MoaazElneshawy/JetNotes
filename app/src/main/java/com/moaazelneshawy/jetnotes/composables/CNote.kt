package com.moaazelneshawy.jetnotes.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moaazelneshawy.jetnotes.models.NoteModel

@ExperimentalMaterialApi
@Composable
fun CNote(modifier: Modifier = Modifier, note: NoteModel, onCheck: (Boolean) -> Unit? = {}) {
    var isChecked by remember {
        mutableStateOf(note.isChecked ?: false)
    }

    Card(
        modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color(note.color?.color!!)),
    ) {

        ListItem(icon = {
            CNoteColor(
                size = 40.dp, borderWidth = 1.dp, color = note.color
            )
        }, secondaryText = {
            Text(
                text = note.content!!, style = TextStyle(
                    fontWeight = FontWeight.Normal, fontSize = 14.sp, letterSpacing = 0.25.sp
                ), maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, text = {
            Text(
                text = note.title!!, style = TextStyle(
                    fontWeight = FontWeight.Normal, fontSize = 16.sp, letterSpacing = 0.15.sp
                ), maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, trailing = {
            if (note.isChecked != null) Checkbox(checked = isChecked, onCheckedChange = {
                isChecked = it
                onCheck.invoke(it)
            })
        })
    }

}