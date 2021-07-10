package com.example.iot.feature.ui

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iot.App
import com.example.iot.R
import com.example.iot.core.data.Result
import com.example.iot.core.ui.BaseFragment
import com.example.iot.databinding.FragmentBandsListBinding
import com.example.iot.feature.data.BandRepository
import com.example.iot.feature.data.BandService
import com.example.iot.feature.data.BandsListItem


class BandsListFragment : BaseFragment<FragmentBandsListBinding>(R.layout.fragment_bands_list),
    BandsListRecyclerAdapter.CallBack {

    private val viewModelFactory = BandsListViewModelFactory(
        BandRepository(
            App.retrofit.create(BandService::class.java)
        ),
        this
    )
    private val viewModel by navGraphViewModels<BandsListViewModel>(R.id.bandListNavGraph) { viewModelFactory }
    lateinit var recyclerViewAdapter: BandsListRecyclerAdapter


    override fun onViewCreated() {
        viewModel.getBands()
        dataBinding?.apply {

            rvBandsList.setHasFixedSize(true)
            rvBandsList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewAdapter = BandsListRecyclerAdapter(this@BandsListFragment)
            rvBandsList.adapter = recyclerViewAdapter

        }
        viewModel.bandsList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    recyclerViewAdapter.submitList(it.data.bands)
                    recyclerViewAdapter.notifyDataSetChanged()
                }
                is Result.Error -> {

                }
                is Result.Loading -> {

                }
            }
        })

    }

    override fun onItemClick(item: BandsListItem) {
        Toast.makeText(requireContext(), item.bandId.toString(), Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            BandsListFragmentDirections.actionBandsListFragmentToAssignBandDialogFragment(
                item.bandId
            )
        )
    }

}