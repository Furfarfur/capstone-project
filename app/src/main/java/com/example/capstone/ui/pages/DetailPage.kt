package com.example.capstone.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.capstone.R
import com.example.capstone.ui.component.BottomBar
import com.example.capstone.ui.component.JetTopBar
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.CapstoneTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun DetailPage(
    goBack: () -> Unit
){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    androidx.compose.material.Scaffold(
        topBar = {JetTopBar(context = LocalContext.current)},
        bottomBar = { BottomBar(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                backgroundColor = BluePrimary
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.fridge),
                    contentDescription = "scan",
                    modifier = Modifier.size(36.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        scaffoldState = scaffoldState
    ){
        Column(modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp)) {
            Image(painter = painterResource(id = R.drawable.banana), contentDescription = null, modifier = Modifier
                .padding(bottom = 20.dp)
                .clip(
                    RoundedCornerShape(20.dp)
                ))

            Text(text = "Date : 11/21/23", fontSize = 18.sp, modifier = Modifier.size(height = 25.dp, width = 500.dp), textAlign = TextAlign.End)
            Text(text = "Banana", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = "This banana, acquired on November 21, 2023, continues to maintain its freshness and appealing appearance, defying the typical lifespan expectations for this fruit; as of now, it still exhibits vibrant coloration and a firm texture, showcasing its remarkable resilience and the efficacy of the storage conditions employed", fontSize = 16.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun DetailPagePreview(){
    CapstoneTheme {
        DetailPage(goBack = {})
    }
}