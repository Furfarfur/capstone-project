package com.example.capstone.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.capstone.R
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.CapstoneTheme
import org.w3c.dom.Text


@Composable
fun JetCard(
    image: String,
    titile: String,
    date: String,
    modifier : Modifier = Modifier
) {
    Box(
        modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier
                .height(270.dp)
                .width(200.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            border = BorderStroke(2.dp, BluePrimary),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                )
//                Image(
//                    painter = painterResource(image), contentDescription = null,
//                )
                Text(text = titile,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 3.dp, top = 10.dp)
                )
                Text(text = date,
                    fontWeight = FontWeight.Thin,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(4.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

//@Preview(showBackground = true, device = Devices.PIXEL)
//@Composable
//fun JetCardPreview(){
//    CapstoneTheme() {
//        JetCard(R.drawable.banana, "Banana", "23/12/23")
//    }
//}