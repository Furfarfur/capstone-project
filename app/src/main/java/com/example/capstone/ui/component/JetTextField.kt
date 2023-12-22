package com.example.capstone.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.BlueSecondary

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetTextField(
    value: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String,
    icon: ImageVector,
    keyboardType : KeyboardType,
    maxline: Int = 1
){
    var visual: VisualTransformation = VisualTransformation.None
    if(keyboardType == KeyboardType.Password){
        visual = PasswordVisualTransformation()
    }
    var input by remember { mutableStateOf("") }
        TextField(
            value = input,
            onValueChange = {
                input = it
                value(it)
            },
            label = {
                Text(text = hint)
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BluePrimary
                )
            },
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.LightGray,
                focusedLabelColor = BluePrimary,
                unfocusedIndicatorColor = BluePrimary,
                focusedIndicatorColor = BluePrimary,
                containerColor = BlueSecondary.copy(.1f)
            ),
            visualTransformation = visual,
            maxLines = maxline,
            modifier = modifier
        )
}
