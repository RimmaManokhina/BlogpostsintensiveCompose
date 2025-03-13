package ru.easycode.blogpostsintensive.navigation

import ru.easycode.blogpostsintensive.R

sealed class BottomItem(val tittle: String, val iconId: Int, val route: String) {
    data object MyProfile : BottomItem("My Profile", R.drawable.my_profile, "My Profile")
    data object Subs : BottomItem("Subs", R.drawable.subs, "Subs")
    data object Search : BottomItem("Search", R.drawable.search, "Search")
}