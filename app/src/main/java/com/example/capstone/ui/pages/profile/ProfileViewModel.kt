package com.example.capstone.ui.pages.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.local.pref.UserPreference
import kotlinx.coroutines.launch

class ProfileViewModel(private val pref: UserPreference) : ViewModel() {
    private val _username = mutableStateOf("")
    val username: State<String> get() = _username

    private val _email = mutableStateOf("")
    val email: State<String> get() = _email

    init {
        getUserDataFromDataStore()
        getEmailDataFromDataStore()
    }

    fun getUserDataFromDataStore() {
        viewModelScope.launch {
            pref.getUsername().asLiveData().observeForever { response ->
                _username.value = response ?: ""
            }
        }
    }

    fun getEmailDataFromDataStore() {
        viewModelScope.launch {
            pref.getEmail().asLiveData().observeForever { response ->
                _email.value = response ?: ""
            }
        }
    }
}