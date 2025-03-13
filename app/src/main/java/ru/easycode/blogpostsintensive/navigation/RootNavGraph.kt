package ru.easycode.blogpostsintensive.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.easycode.blogpostsintensive.login.presentation.LoginScreenInner

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.LOGINSCREEN
    ) {
        composable(route = Graph.HOME) {
            HomeScreen()
        }
        composable(route = Graph.LOGINSCREEN) {
            LoginScreen(navController)
        }
    }
}

object Graph {
    const val HOME = "home_graph"
    const val LOGINSCREEN = "login_screen"
}

@Composable
fun LoginScreen(navHostController: NavHostController) {
    LoginScreenInner {
        navHostController.navigate(Graph.HOME) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}