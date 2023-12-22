package com.example.capstone.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String){
    object Home : Screen("Home")
    object Favorite : Screen("Favorite")
    object Profile : Screen("Profile")
    object Setting :Screen("Setting")
    object UploadImage: Screen("upload")

}