package com.example.iot

import com.example.iot.databinding.FragmentHomeBinding
import org.neshan.common.model.LatLng

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated() {
        initMap()
    }

    private fun initMap() {
        dataBinding?.let {
            it.map.apply {
                moveCamera(LatLng(57.7510, -97.8220), 0f)
                setZoom(14f, 0f)
                isTrafficEnabled = true
            }
        }
    }

}