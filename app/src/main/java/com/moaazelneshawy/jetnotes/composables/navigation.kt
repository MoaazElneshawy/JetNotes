package com.moaazelneshawy.jetnotes.composables

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moaazelneshawy.jetnotes.MainViewModel
import com.moaazelneshawy.jetnotes.routes.Screens
import com.moaazelneshawy.jetnotes.routes.Screens.Companion.IS_NEW
import com.moaazelneshawy.jetnotes.routes.Screens.Companion.NOTE_MODEL

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CAppNavigation(
    navController: NavHostController,
    viewModel: MainViewModel,
    openNavigationDrawer: () -> Unit
) {

    NavHost(navController = navController, startDestination = Screens.Notes.routeName) {
        composable(Screens.Notes.routeName) {
            CNotesScreen(
                navController,
                viewModel,
                openNavigationDrawer
            )
        }
        composable(Screens.Trash.routeName) {
            CTrashScreen(
                navController,
                viewModel,
                openNavigationDrawer
            )
        }
        composable(
            Screens.NoteInfo.routeName + "/{$IS_NEW}/{$NOTE_MODEL}",
            arguments = listOf(
                navArgument(NOTE_MODEL) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }

            )
        ) { entry ->
            CNotesInfo(
                isNew = entry.arguments?.getBoolean(IS_NEW) ?: true,
                noteModel = entry.arguments?.getString(NOTE_MODEL),
                viewModel = viewModel
            ) { navController.popBackStack() }
        }
    }
}
