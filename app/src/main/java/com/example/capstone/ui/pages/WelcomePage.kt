package com.example.capstone.ui.pages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.capstone.R
import com.example.capstone.data.local.pref.UserPreference
import com.example.capstone.data.local.pref.dataStore
import com.example.capstone.data.remote.retrofit.ApiConfig
import com.example.capstone.data.repository.AuthRepository
import com.example.capstone.ui.ViewModelFactory
import com.example.capstone.ui.component.JetButton
import com.example.capstone.ui.pages.login.LoginPage
import com.example.capstone.ui.pages.login.LoginViewModel
import com.example.capstone.ui.pages.register.SignUpPage
import com.example.capstone.ui.theme.BlackPrimary
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.CapstoneTheme

class WelcomePage: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            CapstoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WelcomePages()
                }
            }
        }
    }

    @Composable
    fun WelcomePages() {
        val viewModelStoreOwner = LocalViewModelStoreOwner.current
        val context = LocalContext.current
        val backColor = Color(0xFF669EB6)

        sessionCheck(context, viewModelStoreOwner!!)
        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(backColor),

            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(id = R.drawable.welcome),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(300.dp)
            )

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.welcomeava),
                        contentDescription = null,
                        modifier = Modifier.height(200.dp)
                    )

                    Column(modifier = Modifier.padding(10.dp)) {}
                    JetButton(onClick = {
                        val intent = Intent(context, LoginPage::class.java)
                        context.startActivity(intent)
                    }, color = BlackPrimary, enabled = true, label = "LOG IN")
                    Column(modifier = Modifier.padding(10.dp)) {}
                    JetButton(onClick = {
                        val intent = Intent(context, SignUpPage::class.java)
                        context.startActivity(intent) }, color = BluePrimary, enabled = true, label = "SIGN UP")
                }
            }

        }
    }

    private fun sessionCheck(context: Context, viewModel: ViewModelStoreOwner){
        val repository = AuthRepository(ApiConfig.getApiService(null))
        val pref = UserPreference.getInstance(context.dataStore)
        val userViewModel = ViewModelProvider(viewModel, ViewModelFactory(repository, pref)).get(
            LoginViewModel::class.java)

        userViewModel.getToken().observe(this){
                token ->
            if (token != "null"){
                val intent = Intent(this, SmartFridgeApp::class.java)
                context.startActivity(intent)
            }
        }
    }

    @Preview(showBackground = true, device = Devices.PIXEL)
    @Composable
    fun WelcomePreview(){
        CapstoneTheme() {
            WelcomePages()
        }
    }
}