package com.moaazelneshawy.jetnotes.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moaazelneshawy.jetnotes.MainViewModel
import com.moaazelneshawy.jetnotes.models.NoteModel
import com.moaazelneshawy.jetnotes.routes.Screens
import com.moaazelneshawy.jetnotes.routes.Screens.Companion.NOTE_MODEL
import com.moaazelneshawy.jetnotes.toJson
import kotlin.random.Random

@Composable
fun CAppNavigation(navController: NavHostController, viewModel: MainViewModel) {

    NavHost(navController = navController, startDestination = Screens.Notes.routeName) {
        composable(Screens.Notes.routeName) { CNotesScreen(navController, viewModel) }
        composable(Screens.Trash.routeName) { CTrashScreen(navController, viewModel) }
        composable(
            Screens.NoteInfo.routeName + "/{$NOTE_MODEL}",
            arguments = listOf(
                navArgument(NOTE_MODEL) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }

            )
        ) { entry ->
            CNotesInfo(
                noteModel = entry.arguments?.getString(NOTE_MODEL),
                viewModel = viewModel
            ) { navController.popBackStack() }
        }
    }
}
