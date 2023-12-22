package com.example.capstone.ui.pages.recipe

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.capstone.R
import com.example.capstone.data.local.entity.RecipeEntity
import com.example.capstone.ui.component.JetButton
import com.example.capstone.ui.component.JetCard
import com.example.capstone.ui.theme.CapstoneTheme


class RecipePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneTheme {
                Surface(
                ) {
                    RecipePages()
                }
            }
        }
    }

    @Composable
    fun RecipePages(context: Context = LocalContext.current) {

        val receivedList = intent.getSerializableExtra("recipeList") as ArrayList<RecipeEntity>?
        Image(
            painter = painterResource(id = R.drawable.corner),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.listpage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 100.dp, height = 100.dp)
                )
            }
            lazyGrid(context, recipeList = receivedList ?: arrayListOf())
        }
    }
    @Composable
    fun lazyGrid(context: Context, recipeList: List<RecipeEntity>){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(recipeList, key = {it.id}) { item ->
                JetCard(image = item.photo, titile = item.name, date = item.likes)
            }
        }
    }
}


