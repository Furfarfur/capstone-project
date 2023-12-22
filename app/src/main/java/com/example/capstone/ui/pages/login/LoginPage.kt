package com.example.capstone.ui.pages.login

import com.example.capstone.data.local.pref.dataStore
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.capstone.R
import com.example.capstone.data.local.pref.UserPreference
import com.example.capstone.data.remote.retrofit.ApiConfig
import com.example.capstone.data.repository.AuthRepository
import com.example.capstone.ui.ViewModelFactory
import com.example.capstone.ui.component.JetButton
import com.example.capstone.ui.component.JetTextField
import com.example.capstone.ui.component.JetToast
import com.example.capstone.ui.pages.WelcomePage
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.CapstoneTheme

class LoginPage: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            CapstoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LoginPages()
                }
            }
        }
    }
    @Composable
    fun LoginPages() {
        val context = LocalContext.current
        val viewModelStoreOwner = LocalViewModelStoreOwner.current
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1f)
                .background(color = Color(android.graphics.Color.parseColor("#ffffff"))),

            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Image(
                painter = painterResource(R.drawable.backgroundwave),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(150.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = null,
                modifier = Modifier.height(250.dp)
            )

            Text(
                text = "LOG IN",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(android.graphics.Color.parseColor("#007CFF"))
            )
            Text(
                modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
                text = "REGISTER YOUR SMART\nFRIDGE ACCOUNT",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color(android.graphics.Color.parseColor("#000000")),
            )

            JetTextField(hint = "EMAIL", icon = Icons.Outlined.Email, keyboardType = KeyboardType.Email, value = { new -> email = new })
            Column(modifier = Modifier.padding(5.dp)) {}
            JetTextField(hint = "PASSWORD", icon = Icons.Outlined.AccountBox, keyboardType = KeyboardType.Password, value = { new -> password = new })
            Column(modifier = Modifier.padding(20.dp)) {}
            JetButton(onClick = {
                login(email, password, context, viewModelStoreOwner!!)
            }, color = BluePrimary, enabled = true, label = "LOG IN")
        }
    }

    @Preview(showBackground = true, device = Devices.PIXEL)
    @Composable
    fun LoginPreview(){
        CapstoneTheme() {
            LoginPages()
        }
    }

    private fun login(email: String, password: String, context: Context, viewModel: ViewModelStoreOwner){
        val intent = Intent(context, WelcomePage::class.java)
        val repository = AuthRepository(ApiConfig.getApiService(null))
        val pref = UserPreference.getInstance(context.dataStore)
        val userViewModel = ViewModelProvider(viewModel, ViewModelFactory(repository, pref)).get(LoginViewModel::class.java)
        val auth = LoginViewModel(repository, pref)
        auth.login(email, password).thenAccept {
                response ->
            val user = response.user!!
            userViewModel.insertToken(user.token!!)
            userViewModel.insertEmail(user.email!!)
            userViewModel.insertUsername(user.name!!)
            JetToast(response.message!!, context)
            context.startActivity(intent)
        }.exceptionally {
                throwable ->
            JetToast("Login Failed", context)
            null
        }
    }
}