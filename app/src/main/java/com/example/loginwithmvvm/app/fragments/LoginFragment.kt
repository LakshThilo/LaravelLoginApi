package com.example.loginwithmvvm.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.loginwithmvvm.app.base.BaseFragment
import com.example.loginwithmvvm.app.viewModel.LoginViewModel
import com.example.loginwithmvvm.data.network.AuthApi
import com.example.loginwithmvvm.data.network.Resource
import com.example.loginwithmvvm.data.repository.AuthRepository
import com.example.loginwithmvvm.databinding.FragmentLoginBinding


class LoginFragment :  BaseFragment<LoginViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            when(it){

                is Resource.Success -> { Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()}

                is Resource.Failure -> { Toast.makeText(requireContext(), "Login fail", Toast.LENGTH_SHORT).show()}
            }

        })


        binding.btnSubmit.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            viewModel.login(email, password)
        }
    }

    override fun getViewModel() = LoginViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    /* this function return BaseRepository , create and return AuthRepository we need
    instance of Api, to create api instance we need instance of remote data source */
    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))

}