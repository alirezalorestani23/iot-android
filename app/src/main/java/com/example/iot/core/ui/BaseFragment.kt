package com.example.iot.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding>(
    private val viewID: Int
) : Fragment() {

    var dataBinding: B? = null
    private var isPageLoaded = false

    abstract fun onViewCreated()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isPageLoaded) {
            onViewCreated()
            isPageLoaded = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (dataBinding == null) {
            performDataBinding(inflater, container)
        }
        return dataBinding?.root
    }

    private fun performDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        dataBinding = DataBindingUtil.inflate(
            inflater, viewID, container, false
        )
        dataBinding?.let {
            it.lifecycleOwner = this
        }
    }


}