package com.example.iot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import org.neshan.common.model.LatLng
import org.neshan.mapsdk.MapView
import org.neshan.mapsdk.style.NeshanMapStyle

class HomeFragment : Fragment() {

    var map: MapView? = null

    // save current map style
    @NeshanMapStyle
    var mapStyle = 0

    // map style control
    var themePreview: ImageView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        map = view.findViewById<MapView>(R.id.map)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun initMap() {
        mapStyle = NeshanMapStyle.STANDARD_DAY

        // Setting map focal position to a fixed position and setting camera zoom
        map!!.moveCamera(LatLng(57.7510, -97.8220), 0f)
        map!!.setZoom(14f, 0f)
        map!!.isTrafficEnabled = true
    }
}