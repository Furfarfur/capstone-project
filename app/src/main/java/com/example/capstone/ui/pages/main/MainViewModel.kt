package com.example.capstone.ui.pages.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.local.pref.UserPreference
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference) : ViewModel() {

    private val _username = mutableStateOf("")
    val username: State<String> get() = _username

    init {
        getUserDataFromDataStore()
    }

    fun getUserDataFromDataStore() {
        viewModelScope.launch {
            pref.getUsername().asLiveData().observeForever { response ->
                _username.value = response ?: ""
            }
        }
    }
}
