package ru.easycode.blogpostsintensive.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.easycode.blogpostsintensive.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    val bottomNavController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = bottomNavController) }
    ) {
        HomeNavGraph(bottomNavController, navController)
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val screens = listOf(
        BottomItem.MyProfile, BottomItem.Subs, BottomItem.Search
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) NavigationBar(
        containerColor = colorResource(R.color.green),
        contentColor = colorResource(R.color.black),
        tonalElevation = 100.dp
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }, icon = {
                    Icon(painter = painterResource(id = screen.iconId), contentDescription = "Icon")
                }, label = {
                    Text(text = screen.tittle, fontSize = 9.sp)
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = colorResource(R.color.black),
                    selectedTextColor = colorResource(R.color.black),
                    selectedIndicatorColor = colorResource(R.color.green),
                    unselectedIconColor = colorResource(R.color.grey),
                    unselectedTextColor = colorResource(R.color.grey),
                    disabledIconColor = colorResource(R.color.grey),
                    disabledTextColor = colorResource(R.color.grey)
                )
            )

        }
    }
}

