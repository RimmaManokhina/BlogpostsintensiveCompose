package ru.easycode.blogpostsintensive.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nestednavigationbottombardemo.screens.ScreenContent
import ru.easycode.blogpostsintensive.subscriptions.SubsScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomItem.MyProfile.route
    ) {
        composable(route = BottomItem.MyProfile.route) {
            ScreenContent(
                name = BottomItem.MyProfile.route,
                onClick = {
                }
            )
        }
        composable(route = BottomItem.Subs.route) {
            SubsScreen()
        }
        composable(route = BottomItem.Search.route) {
            ScreenContent(
                name = BottomItem.Search.route,
                onClick = { }
            )
        }
    }
}

