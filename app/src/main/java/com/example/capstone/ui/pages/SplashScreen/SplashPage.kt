package com.example.capstone.ui.pages.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.capstone.R
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.CapstoneTheme

@Composable
fun SplashPage(){

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(BluePrimary)){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {

            Image(painter = painterResource(R.drawable.smarfridgeapp), contentDescription = null, modifier = Modifier.fillMaxWidth())
            Image(painter = painterResource(R.drawable.logofridgeapp), contentDescription = null, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPagePreview(){
    CapstoneTheme {
        SplashPage()
    }
}