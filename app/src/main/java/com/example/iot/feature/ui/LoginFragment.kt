package com.example.iot

import androidx.navigation.fragment.findNavController
import com.example.iot.databinding.FragmentHomeBinding
import com.google.android.material.button.MaterialButton


class LoginFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_login) {

    override fun onViewCreated() {
        val button = view?.findViewById<MaterialButton>(R.id.btn_login)
        button?.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }
}