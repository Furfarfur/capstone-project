package com.example.capstone.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.BlueSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetSearchBar(
    modifier: Modifier = Modifier,
    placeholder: String
){
    var input by remember { mutableStateOf("") }
    TextField(
            value = input,
            onValueChange = {input = it},
            label = {
                    Text(text = placeholder)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = BluePrimary
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = BlueSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = modifier,
            maxLines = 1
        )
}