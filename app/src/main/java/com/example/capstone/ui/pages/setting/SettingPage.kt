package com.example.capstone.ui.pages.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.capstone.R
import com.example.capstone.data.local.pref.UserPreference
import com.example.capstone.data.local.pref.dataStore
import com.example.capstone.ui.component.JetButton
import com.example.capstone.ui.pages.WelcomePage
import com.example.capstone.ui.pages.login.LoginViewModel
import com.example.capstone.ui.pages.profile.ProfileViewModel
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.CapstoneTheme


class SettingPage: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneTheme {
                Surface(
                ) {
                    SettingAccount()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SettingAccount() {
        val context = LocalContext.current
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val pref = UserPreference.getInstance(this.dataStore)
        val profileViewModel = ProfileViewModel(pref)
        LaunchedEffect(true) {
            profileViewModel.getUserDataFromDataStore()
        }
        val username by profileViewModel.username
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        ) { innerPadding ->
            Box(
                Modifier.fillMaxSize(),
            ){
                Image(
                    painter = painterResource(id = R.drawable.background2profile),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .matchParentSize()
                        .padding(innerPadding)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Image(
                painter = painterResource(id = R.drawable.photoprofile),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
            )
            Text(
                text = username,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            JetButton(onClick = { logout(context) }, color = BluePrimary, enabled = true, label = "LOGOUT")

        }
    }

    @Preview(showBackground = true, device = Devices.PIXEL)
    @Composable
    fun SettingPreview(){
        CapstoneTheme {
            SettingAccount()
        }
    }

    private fun logout(context: Context){
        val intent = Intent(context, WelcomePage::class.java)
        val pref = UserPreference.getInstance(dataStore)
        val logout = SettingViewModel(pref)
        logout.destroySession()
        context.startActivity(intent)
    }
}

