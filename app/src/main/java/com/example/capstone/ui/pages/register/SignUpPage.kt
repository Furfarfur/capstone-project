package com.example.capstone.ui.pages.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.R
import com.example.capstone.data.local.pref.UserPreference
import com.example.capstone.data.local.pref.dataStore
import com.example.capstone.data.remote.retrofit.ApiConfig
import com.example.capstone.data.repository.AuthRepository
import com.example.capstone.ui.ViewModelFactory
import com.example.capstone.ui.component.JetButton
import com.example.capstone.ui.component.JetTextField
import com.example.capstone.ui.component.JetToast
import com.example.capstone.ui.pages.WelcomePage
import com.example.capstone.ui.pages.login.LoginViewModel
import com.example.capstone.ui.theme.BlackPrimary
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.BlueText
import com.example.capstone.ui.theme.CapstoneTheme

class SignUpPage: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SignUpPages()
                }
            }
        }
    }
    @Composable
    fun SignUpPages(){
        val context = LocalContext.current
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.regist),
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .padding(top = 50.dp)
            )
            Text(
                text = "SIGN UP",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = BlueText
            )
            Text(
                modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
                text = "REGISTER YOUR SMART\nFRIDGE ACCOUNT",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = BlackPrimary,
            )
            JetTextField(hint = "USERNAME", icon = Icons.Outlined.Person, keyboardType = KeyboardType.Text, value = { new -> username = new })
            Column(modifier = Modifier.padding(5.dp)){}
            JetTextField(hint = "EMAIL", icon = Icons.Outlined.Email, keyboardType = KeyboardType.Email, value = { new -> email = new })
            Column(modifier = Modifier.padding(5.dp)){}
            JetTextField(hint = "PASSWORD", icon = Icons.Outlined.AccountBox, keyboardType = KeyboardType.Password, value = { new -> password = new })
            Column(modifier = Modifier.padding(20.dp)){}
            JetButton(onClick = { register(username, email, password, context) }, color = BluePrimary, enabled = true, label = "SIGN UP")
        }
    }

    private fun register(username: String, email: String, password: String, context: Context){
        val repository = AuthRepository(ApiConfig.getApiService(null))
        val pref = UserPreference.getInstance(context.dataStore)
        val userViewModel = ViewModelProvider(this, ViewModelFactory(repository, pref)).get(
            LoginViewModel::class.java)
        val auth = SignUpViewModel(repository)
        val intent = Intent(context, WelcomePage::class.java)
        auth.register(username, email, password).thenAccept{
                response ->
            val user = response.user!!
            userViewModel.insertToken(user.token!!)
            userViewModel.insertEmail(user.email!!)
            userViewModel.insertUsername(user.name!!)
            JetToast(response.message!!, context)
            context.startActivity(intent)
        }.exceptionally {throwable ->
            JetToast("Register Failed", context)
            null
        }
    }


}