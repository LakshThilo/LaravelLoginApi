package com.example.loginwithmvvm.app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginwithmvvm.data.local.response.LoginResponse
import com.example.loginwithmvvm.data.network.Resource
import com.example.loginwithmvvm.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository
): ViewModel() {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    val loginResponse: LiveData<Resource<LoginResponse>>
    get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {

        _loginResponse.value = repository.login(email, password)
    }
}