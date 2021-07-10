package com.example.iot.feature.ui

import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iot.App
import com.example.iot.R
import com.example.iot.core.data.Result
import com.example.iot.core.ui.BaseFragment
import com.example.iot.databinding.FragmentGuardsListBinding
import com.example.iot.feature.data.GuardRepository
import com.example.iot.feature.data.GuardService
import com.example.iot.feature.data.GuardsListItem

class GuardsListFragment : BaseFragment<FragmentGuardsListBinding>(R.layout.fragment_guards_list),
    GuardsListRecyclerAdapter.CallBack {

    private val viewModel by viewModels<GuardsListViewModel> {
        GuardsListViewModelFactory(
            GuardRepository(
                App.retrofit.create(GuardService::class.java),
                App.roomDatabase.getGuardDao()
            ),
            this
        )
    }

    lateinit var recyclerViewAdapter: GuardsListRecyclerAdapter


    override fun onViewCreated() {
        dataBinding?.apply {

            rvGuardsList.setHasFixedSize(true)
            rvGuardsList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewAdapter = GuardsListRecyclerAdapter(this@GuardsListFragment)
            rvGuardsList.adapter = recyclerViewAdapter

        }
        viewModel.getGuards()
        viewModel.guardsList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    recyclerViewAdapter.submitList(it.data.guards)
                    recyclerViewAdapter.notifyDataSetChanged()
                }
                is Result.Error -> {

                }
                is Result.Loading -> {

                }
            }
        })
    }

    override fun onItemClick(item: GuardsListItem) {
        val staffId = item.staffId
        findNavController().navigate(
            GuardsListFragmentDirections.actionGuardsListFragmentToGuardProfileFragment(staffId)
        )
//        Toast.makeText(requireContext(), item.name, Toast.LENGTH_SHORT).show()
    }
}