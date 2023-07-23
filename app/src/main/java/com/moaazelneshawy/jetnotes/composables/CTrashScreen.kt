package com.moaazelneshawy.jetnotes.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.moaazelneshawy.jetnotes.MainViewModel
import com.moaazelneshawy.jetnotes.routes.Screens


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CTrashScreen(
    navController: NavController,
    viewModel: MainViewModel,
    openNavigationDrawer: () -> Unit
) {

    val notes = viewModel.getAllNotes(true).collectAsState(initial = listOf())
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppDrawerHeader { openNavigationDrawer.invoke() } }
    ) {
        if (notes.value.isEmpty())
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "No Notes in Trash",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        else
            LazyColumn(content = {
                items(notes.value) { note ->
                    CNote(note = note, onCheck = {
                        val updatedNote = note.copy(isChecked = it)
                        viewModel.updateNote(updatedNote)
                    }, modifier = Modifier.clickable {
                        viewModel.selectNote(note)
                        val route = Screens.NoteInfo.routeName
                        navController.navigate(route)
                    })
                }
            })
    }
}
