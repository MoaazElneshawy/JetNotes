package com.moaazelneshawy.jetnotes.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.moaazelneshawy.jetnotes.MainViewModel
import com.moaazelneshawy.jetnotes.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CNotesInfo(
    isNew: Boolean,
    noteModel: String? = null,
    viewModel: MainViewModel,
    navigateBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SaveNotesAppBar(
                isNew = isNew,
                onNavigationIconClicked = navigateBack::invoke,
                onSave = { },
                onChooseColor = { },
                onDeleteNote = {})
        }
    ) {
        Text(text = "$noteModel")
    }
}

@Composable
fun SaveNotesAppBar(
    isNew: Boolean,
    onNavigationIconClicked: () -> Unit,
    onSave: () -> Unit,
    onChooseColor: () -> Unit,
    onDeleteNote: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary,
        title = {
            Text(
                text = if (isNew) "Create Note" else "Edit Note",
                color = MaterialTheme.colors.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClicked.invoke() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        actions = {
            IconButton(onClick = { onSave.invoke() }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "save",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            IconButton(onClick = { onChooseColor.invoke() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_color_lens_24),
                    contentDescription = "save",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            IconButton(onClick = { onDeleteNote.invoke() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "save",
                    tint = MaterialTheme.colors.onPrimary
                )
            }

        }
    )
}