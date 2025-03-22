package ru.easycode.blogpostsintensive.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.easycode.blogpostsintensive.login.presentation.LoginScreenInner
import ru.easycode.blogpostsintensive.navigation.OtherProfileArgs.Companion.OTHER_PROFILE_ROUTE
import ru.easycode.blogpostsintensive.profile.other.presentation.OtherProfileScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.LOGIN
    ) {
        composable(route = Graph.HOME) {
            HomeScreen(navController)
        }
        composable(route = Graph.LOGIN) {
            LoginScreen(navController)
        }
        composable(route = OTHER_PROFILE_ROUTE) { navBackStackEntry ->
            OtherProfileArgs.from(navBackStackEntry)?.let { args ->
                OtherProfileScreen(
                    userId = args.userId,
                    userName = args.userName,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}

object Graph {
    const val HOME = "home_screen"
    const val LOGIN = "login_screen"
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


data class OtherProfileArgs(val userId: String, val userName: String) {
    companion object {
        private const val USER_NAME = "userName"
        private const val USER_ID = "userId"
        const val OTHER_PROFILE_ROUTE = "otherProfile/{$USER_ID}/{$USER_NAME}"

        fun from(navBackStackEntry: NavBackStackEntry): OtherProfileArgs? {
            val userId = navBackStackEntry.arguments?.getString(USER_ID) ?: return null
            val userName = navBackStackEntry.arguments?.getString(USER_NAME).orEmpty()
            return OtherProfileArgs(userId, userName)
        }
    }
}

fun NavController.navigateToOtherProfile(userId: String, userName: String) {
    this.navigate("otherProfile/$userId/$userName")
}