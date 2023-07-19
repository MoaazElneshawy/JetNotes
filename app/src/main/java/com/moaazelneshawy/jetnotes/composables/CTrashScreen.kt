package com.moaazelneshawy.jetnotes.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.moaazelneshawy.jetnotes.MainViewModel


@Composable
fun CTrashScreen(navController: NavController, viewModel: MainViewModel) {
    val notes = viewModel.getAllNotes(true).collectAsState(initial = listOf())
    if (notes.value.isEmpty())
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "No Notes exists",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    else LazyColumn(content = {
        items(notes.value) { note ->
            CNote(note = note, onCheck = {
                viewModel.updateNote(it.copy(isDeleted = false))
            })
        }
    })
}
