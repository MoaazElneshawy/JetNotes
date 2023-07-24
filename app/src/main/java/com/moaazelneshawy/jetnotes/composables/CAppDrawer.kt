package com.moaazelneshawy.jetnotes.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moaazelneshawy.jetnotes.ui.theme.JetNotesThemeSettings

// header
@Composable
fun AppDrawerHeader(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onClick) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "drawer_header_icon")
        }

        Text(
            text = "JetNotes",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 15.dp)
        )
    }
}

// drawer item
@Composable
fun AppDrawerItem(icon: ImageVector, title: String, isSelected: Boolean, onClick: () -> Unit) {

    val colors = MaterialTheme.colors

    // imageAlpha
    val imageAlpha = if (isSelected) 1f else 0.6f

    val textColor = if (isSelected)
        colors.primary
    else
        colors.onSurface.copy(alpha = 0.6f)

    val backgroundColor = if (isSelected)
        colors.primary.copy(alpha = 0.12f)
    else
        colors.surface

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
        color = backgroundColor,
        shape = RectangleShape
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clickable { onClick.invoke() },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = icon,
                contentDescription = null,
                alpha = imageAlpha,
                colorFilter = ColorFilter.tint(textColor)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = title, color = textColor, modifier = Modifier.fillMaxWidth())
        }
    }

}

@Composable
fun AppDarkTheme() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Dark Theme Mode",
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp)
        )
        Switch(
            checked = JetNotesThemeSettings.isDarkThemeEnabled,
            onCheckedChange = { JetNotesThemeSettings.isDarkThemeEnabled = it })
    }
}