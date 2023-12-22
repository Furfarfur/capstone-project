package com.example.capstone.ui.pages.setting

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.local.pref.UserPreference
import kotlinx.coroutines.launch

class SettingViewModel(private val preference: UserPreference): ViewModel() {
    fun destroySession(){
        viewModelScope.launch {
            preference.saveSession("null")
            preference.saveEmail("null")
            preference.saveUsername("null")
        }
    }
}