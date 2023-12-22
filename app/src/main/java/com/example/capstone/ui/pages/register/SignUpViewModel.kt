package com.example.capstone.ui.pages.register

import androidx.lifecycle.ViewModel
import com.example.capstone.data.repository.AuthRepository

class SignUpViewModel(private val authRepository: AuthRepository): ViewModel() {
    fun register(username: String, email: String, password: String) = authRepository.insertRegister(username, email, password)
}