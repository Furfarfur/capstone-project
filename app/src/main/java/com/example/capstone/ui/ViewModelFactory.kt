package com.example.capstone.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.local.pref.UserPreference
import com.example.capstone.data.repository.AuthRepository
import com.example.capstone.ui.pages.login.LoginViewModel

class ViewModelFactory(private val authRepository: AuthRepository, private val preference: UserPreference): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return  when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepository, preference) as T
        }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel Class " + modelClass.name)
            }
        }
    }


//    companion object {
//        @Volatile
//        private var INSTANCE: ViewModelFactory? = null
//        @JvmStatic
//        fun getInstance(context: Context): ViewModelFactory {
//            if (INSTANCE == null) {
//                synchronized(ViewModelFactory::class.java) {
//                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
//                }
//            }
//            return INSTANCE as ViewModelFactory
//        }
//    }
}