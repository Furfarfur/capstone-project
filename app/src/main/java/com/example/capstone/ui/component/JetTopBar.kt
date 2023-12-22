package com.example.capstone.ui.component

import android.content.Context
import android.content.Intent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext

import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.capstone.ui.pages.SmartFridgeApp
import com.example.capstone.ui.theme.CapstoneTheme
import com.example.capstone.ui.theme.BluePrimary
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetTopBar(
    navController: NavHostController? = null,
//    goBack: () -> Unit,
    context: Context
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BluePrimary,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {

        },
        navigationIcon = {
            IconButton(onClick = {
                val intent = Intent(context, SmartFridgeApp::class.java)
                context.startActivity(intent)
               })
            {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
//        actions = {
//            IconButton(onClick = { /* do something */ }) {
//                Icon(
//                    imageVector = Icons.Filled.Menu,
//                    contentDescription = "Localized description"
//                )
//            }
//        },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
@Preview(showBackground = true)
fun JetTopBarPreview(){
    CapstoneTheme {
//        JetTopBar(
////            goBack = {}
//        )
    }
}
