package com.moaazelneshawy.jetnotes.composables

import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi
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

@ExperimentalMaterialApi
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
            Screens.NoteInfo.routeName
        ) {
            CNotesInfo(
                viewModel = viewModel
            ) { navController.popBackStack() }
        }
    }
}
