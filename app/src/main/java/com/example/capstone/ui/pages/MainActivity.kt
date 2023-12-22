package com.example.capstone.ui.pages

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.capstone.ui.pages.SplashScreen.SplashPage
import com.example.capstone.ui.theme.CapstoneTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val context = LocalContext.current
    var splashShown by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(2000)
        splashShown = true
    }
    if (splashShown){
        val intent = Intent(context, WelcomePage::class.java)
        context.startActivity(intent)
    }
    else{
        SplashPage()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CapstoneTheme {
        Greeting()
    }
}