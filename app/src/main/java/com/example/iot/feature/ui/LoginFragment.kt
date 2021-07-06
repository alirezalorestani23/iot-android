package com.example.iot.feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iot.R
import com.example.iot.feature.ui.LoginFragmentDirections.Companion.actionLoginFragmentToHomeFragment
import com.google.android.material.button.MaterialButton


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<MaterialButton>(R.id.button)
        button.setOnClickListener {
            findNavController().navigate(actionLoginFragmentToHomeFragment())
        }


    }
}