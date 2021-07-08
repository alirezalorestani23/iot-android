package com.example.iot.feature.ui

import android.graphics.BitmapFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.carto.graphics.Color
import com.carto.styles.*
import com.carto.utils.BitmapUtils
import com.example.iot.App.Companion.retrofit
import com.example.iot.App.Companion.roomDatabase
import com.example.iot.core.Constant.universityArea
import com.example.iot.core.data.Result
import com.example.iot.core.ui.BaseFragment
import com.example.iot.databinding.FragmentHomeBinding
import com.example.iot.feature.data.ActiveGuardsResponse
import com.example.iot.feature.data.GuardLastHistoryResponse
import com.example.iot.feature.data.GuardRepository
import com.example.iot.feature.data.GuardService
import org.neshan.common.model.LatLng
import org.neshan.mapsdk.MapView
import org.neshan.mapsdk.R
import org.neshan.mapsdk.model.Marker
import org.neshan.mapsdk.model.Polygon
import com.example.iot.R as ProjectR


class HomeFragment : BaseFragment<FragmentHomeBinding>(ProjectR.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(
            GuardRepository(retrofit.create(GuardService::class.java), roomDatabase.getGuardDao()),
            this
        )
    }

    private var polygon: Polygon? = null
    var animSt: AnimationStyle? = null

    override fun onViewCreated() {
        initMap()
        viewModel.fetchActiveGuards()
        viewModel.activeGuards.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    it.data.activeGuardsList.forEach { activeGuard ->
                        viewModel.getGuardLastHistory(activeGuard.staffId)
                    }
                }
                is Result.Error -> {

                }
                is Result.Loading -> {

                }

            }
        })
        viewModel.guardLastHistory.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    addGuardMarker(it.data)

                }
                is Result.Error -> {

                }
                is Result.Loading -> {

                }

            }
        })
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

    private fun addGuardMarker(guardLastHistory: GuardLastHistoryResponse) {
        if (guardLastHistory.last != null)
            dataBinding?.map?.addMarker(
                createMarker(
                    LatLng(
                        guardLastHistory.last.lat,
                        guardLastHistory.last.lang
                    )
                )
            )
    }


    private fun createMarker(loc: LatLng): Marker {
        // Creating animation for marker. We should use an object of type AnimationStyleBuilder, set
        // all animation features on it and then call buildStyle() method that returns an object of type
        // AnimationStyle
        val animStBl = AnimationStyleBuilder()
        animStBl.fadeAnimationType = AnimationType.ANIMATION_TYPE_SMOOTHSTEP
        animStBl.sizeAnimationType = AnimationType.ANIMATION_TYPE_SPRING
        animStBl.phaseInDuration = 0.5f
        animStBl.phaseOutDuration = 0.5f
        animSt = animStBl.buildStyle()

        // Creating marker style. We should use an object of type MarkerStyleCreator, set all features on it
        // and then call buildStyle method on it. This method returns an object of type MarkerStyle
        val markStCr = MarkerStyleBuilder()
        markStCr.size = 30f
        markStCr.bitmap = BitmapUtils.createBitmapFromAndroidBitmap(
            BitmapFactory.decodeResource(
                resources, R.drawable.ic_marker
            )
        )
        // AnimationStyle object - that was created before - is used here
        markStCr.animationStyle = animSt
        val markSt = markStCr.buildStyle()

        // Creating marker
        return Marker(loc, markSt)
    }
}