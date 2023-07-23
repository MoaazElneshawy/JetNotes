package com.moaazelneshawy.jetnotes.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moaazelneshawy.jetnotes.models.ColorModel
import com.moaazelneshawy.jetnotes.models.NoteModel

@Composable
fun CNote(modifier: Modifier = Modifier, note: NoteModel, onCheck: (Boolean) -> Unit? = {}) {
    var  isChecked by remember {
        mutableStateOf(note.isChecked ?: false)
    }

    Row(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(4.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        CNoteColor(
            size = 40.dp,
            borderWidth = 1.dp,
            color = note.color ?: ColorModel.YELLOW
        )

        Column(
            Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = note.title!!, style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp
                ), maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = note.content!!,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (note.isChecked != null)
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    onCheck.invoke(it)
                }
            )
    }

}