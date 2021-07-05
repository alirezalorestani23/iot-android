package com.example.iot

import com.carto.graphics.Color
import com.carto.styles.LineStyle
import com.carto.styles.LineStyleBuilder
import com.carto.styles.PolygonStyle
import com.carto.styles.PolygonStyleBuilder
import com.example.iot.Constant.universityArea
import com.example.iot.databinding.FragmentHomeBinding
import org.neshan.common.model.LatLng
import org.neshan.mapsdk.MapView
import org.neshan.mapsdk.model.Polygon

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private var polygon: Polygon? = null
    override fun onViewCreated() {
        initMap()
    }

    private fun initMap() {
        dataBinding?.let {
            it.map.apply {
                drawPolygon(this)
                moveCamera(LatLng(36.315846, 59.530222), 0f)
                setZoom(14f, 0f)
                isTrafficEnabled = true
            }
        }
    }

    private fun drawPolygon(map: MapView) {
        removeLastPolygon()
        polygon = Polygon(universityArea, getPolygonStyle())
        map.addPolygon(polygon)
    }

    private fun removeLastPolygon() {
        if (polygon != null) {
            dataBinding?.map?.removePolygon(polygon)
        }
    }

    private fun getPolygonStyle(): PolygonStyle {
        val polygonStCr = PolygonStyleBuilder()
        polygonStCr.lineStyle = getLineStyle()
        polygonStCr.color = Color(0)
        return polygonStCr.buildStyle()
    }

    private fun getLineStyle(): LineStyle {
        val lineStCr = LineStyleBuilder()
        lineStCr.color = Color(2.toShort(), 119.toShort(), 189.toShort(), 190.toShort())
        lineStCr.width = 2f
        lineStCr.stretchFactor = 1f
        return lineStCr.buildStyle()
    }
}

/**
 *
 * 36.315846, 59.530222
 * 36.313339, 59.529253
 * 36.310954, 59.533420
 * 36.307406, 59.520140
 * 36.303639, 59.530528
 *
 *
 *
 * */