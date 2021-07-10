package com.example.iot.feature.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.example.iot.R
import com.example.iot.core.data.Result
import com.example.iot.databinding.FragmentAssignBandDialogBinding
import com.example.iot.feature.data.EditBandGuardBody

class AssignBandDialogFragment : DialogFragment() {

    var bandId: Int = 0

    private val viewModel by navGraphViewModels<BandsListViewModel>(R.id.bandListNavGraph)
    lateinit var dataBinding: FragmentAssignBandDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentAssignBandDialogBinding
            .inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
            }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bandId = arguments?.getInt("bandId") as Int

        dataBinding.apply {
            btnAssignBandSubmit.setOnClickListener {
                viewModel.assignBand(
                    EditBandGuardBody(
                        bandId ?: 0,
                        etAssignBand.text.toString().toInt()
                    )
                )
            }
        }

        viewModel.editBand.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    dismiss()
                }
                is Result.Loading -> {
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), "cant assign right now!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })


        super.onViewCreated(view, savedInstanceState)
    }
}