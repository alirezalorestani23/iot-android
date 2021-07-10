package com.example.iot.feature.ui

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.iot.App
import com.example.iot.R
import com.example.iot.core.data.Result
import com.example.iot.core.ui.BaseFragment
import com.example.iot.databinding.FragmentGuardProfileBinding
import com.example.iot.feature.data.GuardRepository
import com.example.iot.feature.data.GuardService

class GuardProfileFragment :
    BaseFragment<FragmentGuardProfileBinding>(R.layout.fragment_guard_profile) {

    var staffId: Int = 0


    private val viewModel by viewModels<GuardProfileViewModel> {
        GuardProfileViewModelFactory(
            GuardRepository(
                App.retrofit.create(GuardService::class.java),
                App.roomDatabase.getGuardDao()
            ),
            this
        )
    }

    override fun onViewCreated() {
        staffId = arguments?.getInt("staffId") as Int

        viewModel.getGuardProfile(staffId)

        viewModel.guardsProfile.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    dataBinding?.guard = it.data


                }
                is Result.Loading -> {

                }
                is Result.Error -> {

                }
            }
        })
        dataBinding?.apply {
            btnDeleteGuard.setOnClickListener {
                viewModel.deleteGuard(staffId)
            }
        }
        viewModel.deleteGuard.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    dataBinding?.ivLoadingProfile?.visibility = View.GONE
                    findNavController().navigateUp()
                }
                is Result.Loading -> {
                    dataBinding?.ivLoadingProfile?.visibility = View.VISIBLE

                }
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "can not delete right now!",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        })
    }
}


