package com.moaazelneshawy.jetnotes.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moaazelneshawy.jetnotes.MainViewModel
import com.moaazelneshawy.jetnotes.R
import com.moaazelneshawy.jetnotes.fromJson
import com.moaazelneshawy.jetnotes.models.ColorModel
import com.moaazelneshawy.jetnotes.models.NoteModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CNotesInfo(
    isNew: Boolean,
    noteModel: String? = null,
    viewModel: MainViewModel,
    navigateBack: () -> Unit
) {
    var currentNote = noteModel?.fromJson<NoteModel>(NoteModel::class.java)
    val canBeChecked: Boolean = currentNote?.isChecked != null

    var title by remember {
        mutableStateOf(currentNote?.title ?: "")
    }
    var content by remember {
        mutableStateOf(currentNote?.content ?: "")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SaveNotesAppBar(
                isNew = isNew,
                onNavigationIconClicked = navigateBack::invoke,
                onSave = {
                    Log.e("CNotesInfo: ", "$currentNote")
                    val note = NoteModel(title, content, ColorModel("Green", Color.Green.toArgb()))
                    viewModel.insertNote(note)
                    navigateBack.invoke()

                },
                onChooseColor = { },
                onDeleteNote = {
                    currentNote?.let { viewModel.deleteNote(it) }
                })
        }
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            NoteColorModel(color = currentNote?.color, modifier = Modifier.clickable {

            })
            Spacer(modifier = Modifier.height(10.dp))
            NoteCheckOption(
                isChecked = canBeChecked,
                onCheckedChange = { canBeCheckedOffNewValue ->
                    val isChecked: Boolean? = if (canBeCheckedOffNewValue)
                        false else null
                    currentNote = currentNote?.copy(isChecked = isChecked)
                }
            )
        }
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
                    contentDescription = "choose color",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            if (!isNew)
                IconButton(onClick = { onDeleteNote.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }

        }
    )
}

@Composable
fun NoteColorModel(color: ColorModel?, modifier: Modifier) {
    Row(
        modifier
            .padding(8.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = color?.colorName ?: "Picked color",
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        CNoteColor(
            color = color ?: ColorModel.YELLOW,
            size = 40.dp,
            modifier = Modifier.padding(4.dp),
            borderWidth = 1.dp
        )
    }
}

@Composable
private fun NoteCheckOption(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        Modifier
            .padding(8.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Can note be checked off?",
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}