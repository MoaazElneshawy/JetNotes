package com.moaazelneshawy.jetnotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moaazelneshawy.jetnotes.composables.AppDarkTheme
import com.moaazelneshawy.jetnotes.composables.AppDrawerHeader
import com.moaazelneshawy.jetnotes.composables.AppDrawerItem
import com.moaazelneshawy.jetnotes.composables.CAppNavigation
import com.moaazelneshawy.jetnotes.routes.Screens
import com.moaazelneshawy.jetnotes.ui.theme.JetNotesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()
            val navBackStackEntry
                    by navController.currentBackStackEntryAsState()
            JetNotesTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                            Column {
                                AppDrawerItem(
                                    Icons.Default.Home,
                                    "Home",
                                    navBackStackEntry?.destination?.route == Screens.Notes.routeName
                                ) {
                                    navController.navigate(Screens.Notes.routeName)
                                    scope.launch { scaffoldState.drawerState.close() }
                                }
                                AppDrawerItem(
                                    Icons.Default.Delete,
                                    "Trash",
                                    navBackStackEntry?.destination?.route == Screens.Trash.routeName
                                ) {
                                    scope.launch { scaffoldState.drawerState.close() }
                                    navController.navigate(Screens.Trash.routeName)
                                }
                                Spacer(modifier = Modifier.height(7.dp))
                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp),
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.height(7.dp))
                                AppDarkTheme()
                            }
                    }
                ) {
                    CAppNavigation(navController, viewModel) {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                }
            }
        }
    }
}