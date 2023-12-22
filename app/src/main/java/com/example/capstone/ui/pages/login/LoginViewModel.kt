package com.example.capstone.ui.pages.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.local.pref.UserPreference
import com.example.capstone.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository, private val preference: UserPreference): ViewModel() {
    fun login(email: String, password: String) = authRepository.login(email, password)

    fun getToken(): LiveData<String>{
        return preference.getSession().asLiveData()
    }

    fun insertToken(token: String){
        viewModelScope.launch {
            preference.saveSession(token)
        }
    }

    fun insertEmail(email: String){
        viewModelScope.launch{
            preference.saveEmail(email)
        }
    }

    fun insertUsername(username: String){
        viewModelScope.launch{
            preference.saveUsername(username)
        }
    }
}