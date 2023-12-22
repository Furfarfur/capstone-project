package com.example.capstone.ui.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.capstone.R
import com.example.capstone.ui.navigation.NavigationItem
import com.example.capstone.ui.navigation.Screen
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.CapstoneTheme


@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            image = ImageVector.vectorResource(id = R.drawable.home_page),
            screen = Screen.Home
        ),
        NavigationItem(
            title = "Favorite",
            image = ImageVector.vectorResource(id = R.drawable.favorite),
            screen = Screen.Favorite
        ),
        NavigationItem(
            title = "Profile",
            image = ImageVector.vectorResource(id = R.drawable.profile),
            screen = Screen.Profile
        ),
        NavigationItem(
            title = "Setting",
            image = ImageVector.vectorResource(id = R.drawable.setting),
            screen = Screen.Setting
        ),

        )
    androidx.compose.material.BottomAppBar(

        cutoutShape = CircleShape,
        contentColor = Color.White,
        backgroundColor = BluePrimary
    ) {
        navigationItems.forEachIndexed { index, navigationItem ->
            if (index == 2) {
                NavigationBarItem(selected = false,
                    onClick = {
//                        navController.navigate(navigationItem.screen.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        restoreState = true
//                        launchSingleTop = true
//                    }
                    } ,
                    icon = { })
            }

            NavigationBarItem(
                selected = (currentRoute == navigationItem.screen.route),
                onClick = {
                    navController.navigate(navigationItem.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = navigationItem.image,
                            contentDescription = navigationItem.title,
                            tint = Color.White,
                            modifier = Modifier
                                .size(36.dp)
                        )
                        Text(
                            text = navigationItem.title,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }

                },
                enabled = true
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun BottomBarPreview() {
    CapstoneTheme {
        BottomBar(
            navController = NavHostController(LocalContext.current)
        )
    }
}