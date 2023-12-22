package com.example.capstone.ui.component

import android.content.Context
import android.widget.Toast

fun JetToast(message: String, context: Context){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}