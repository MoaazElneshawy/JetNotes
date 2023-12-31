package com.moaazelneshawy.jetnotes.composables

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomDrawer
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moaazelneshawy.jetnotes.MainViewModel
import com.moaazelneshawy.jetnotes.R
import com.moaazelneshawy.jetnotes.models.ColorModel
import com.moaazelneshawy.jetnotes.models.NoteModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CNotesInfo(
    viewModel: MainViewModel, navigateBack: () -> Unit
) {
    val currentNote = viewModel.selectedNote.observeAsState()

    var canBeChecked: Boolean by remember {
        mutableStateOf(currentNote.value?.isChecked != null)
    }

    var title by remember {
        mutableStateOf(currentNote.value?.title ?: "")
    }
    var content by remember {
        mutableStateOf(currentNote.value?.content ?: "")
    }
    var noteColor by remember {
        mutableStateOf(currentNote.value?.color ?: ColorModel.YELLOW)
    }

    val scope = rememberCoroutineScope()

    val bottomDrawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)

    val showConfirmDelete = rememberSaveable {
        mutableStateOf(false)
    }

    val isDelete = currentNote.value?.isDeleted


    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        SaveNotesAppBar(isNew = currentNote.value == null,
            onNavigationIconClicked = navigateBack::invoke,
            deleteIcon = if (currentNote.value?.isDeleted == true) R.drawable.baseline_restore_from_trash_24 else R.drawable.baseline_delete_24,
            onSave = {
                if (currentNote.value == null) {
                    val note = NoteModel(title, content, noteColor, canBeChecked)
                    viewModel.insertNote(note)
                } else {
                    val note = currentNote.value?.copy(
                        title = title,
                        content = content,
                        color = noteColor,
                        isChecked = if (canBeChecked) true else null,
                        isDeleted = false
                    )
                    if (note != null) {
                        viewModel.updateNote(note)
                    }
                }
                navigateBack.invoke()
            },
            onChooseColor = { scope.launch { bottomDrawerState.open() } },
            onDeleteNote = {
                showConfirmDelete.value = true
            })
    }) {

        BottomDrawer(drawerState = bottomDrawerState, drawerContent = {
            ColorsModels { selectedColor ->
                noteColor = selectedColor
                scope.launch { bottomDrawerState.close() }
            }
        }) {
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
                NoteColorModel(color = noteColor)
                Spacer(modifier = Modifier.height(10.dp))
                NoteCheckOption(isChecked = canBeChecked,
                    onCheckedChange = { canBeCheckedOffNewValue ->
                        canBeChecked = canBeCheckedOffNewValue
                    })
            }
        }
        if (showConfirmDelete.value)
            AlertDialog(onDismissRequest = {
                showConfirmDelete.value = false
            },
                confirmButton = {
                    TextButton(onClick = {
                        showConfirmDelete.value = false
                        currentNote.value?.let {
                            val newNote =
                                it.copy(isDeleted = currentNote.value?.isDeleted?.not())
                            viewModel.updateNote(newNote)
                        }
                        viewModel.selectNote(null)
                        navigateBack.invoke()
                    }) {
                        Text(text = if (isDelete == true) "Restore" else "Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showConfirmDelete.value = false }) {
                        Text(text = "Cancel")
                    }
                },
                title = { Text(text = if (isDelete == true) "Restore Note" else "Delete Note") },
                text = { Text(text = "Are you sure you want to ${if (isDelete == true) "restore" else "delete"} note ?") })
    }
}

@Composable
fun SaveNotesAppBar(
    isNew: Boolean,
    onNavigationIconClicked: () -> Unit,
    @DrawableRes deleteIcon: Int,
    onSave: () -> Unit,
    onChooseColor: () -> Unit,
    onDeleteNote: () -> Unit
) {
    TopAppBar(modifier = Modifier.fillMaxWidth(),
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
            if (!isNew) IconButton(onClick = { onDeleteNote.invoke() }) {
                Icon(
                    painterResource(id = deleteIcon),
                    contentDescription = "delete",
                    tint = MaterialTheme.colors.onPrimary
                )
            }

        })
}

@Composable
fun NoteColorModel(color: ColorModel?, modifier: Modifier = Modifier) {
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
    isChecked: Boolean, onCheckedChange: (Boolean) -> Unit
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