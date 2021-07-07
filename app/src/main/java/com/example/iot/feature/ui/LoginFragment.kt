package com.example.iot.feature.ui

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.iot.App
import com.example.iot.R
import com.example.iot.core.data.Result
import com.example.iot.core.ui.BaseFragment
import com.example.iot.databinding.FragmentLoginBinding
import com.example.iot.feature.data.LoginRepository
import com.example.iot.feature.data.LoginService


class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(
            LoginRepository(
                App.retrofit.create(LoginService::class.java),
            ),
            this
        )
    }

    override fun onViewCreated() {
        dataBinding?.vm = viewModel

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), it.data?.message, Toast.LENGTH_SHORT).show()

                }
                is Result.Loading -> {

                }
            }
        })
    }


}