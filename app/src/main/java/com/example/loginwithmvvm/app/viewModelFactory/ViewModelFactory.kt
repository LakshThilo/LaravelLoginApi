package com.example.loginwithmvvm.app.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginwithmvvm.app.viewModel.LoginViewModel
import com.example.loginwithmvvm.data.repository.AuthRepository
import com.example.loginwithmvvm.data.repository.BaseRepository

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return when{

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository as AuthRepository) as T

            else -> throw IllegalArgumentException("ViewModelClass not found")
        }

    }
}