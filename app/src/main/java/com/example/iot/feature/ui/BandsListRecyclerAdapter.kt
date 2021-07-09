package com.example.iot.feature.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.iot.R
import com.example.iot.databinding.ItemBandsListBinding
import com.example.iot.feature.data.BandsListItem

class BandsListRecyclerAdapter(
    val callback: CallBack
) : ListAdapter<BandsListItem, BandsListRecyclerAdapter.RecyclerViewHolder>(object :
    DiffUtil.ItemCallback<BandsListItem>() {
    override fun areItemsTheSame(oldItem: BandsListItem, newItem: BandsListItem): Boolean {
        return oldItem.bandId == newItem.bandId
    }

    override fun areContentsTheSame(oldItem: BandsListItem, newItem: BandsListItem): Boolean {
        return true

    }
}) {

    interface CallBack {
        fun onItemClick(item: BandsListItem)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {


        val mBinding: ItemBandsListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_bands_list,
            parent,
            false
        )

        return RecyclerViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = getItem(position)
        holder.uBinding.band = item
        holder.itemView.tag = item
        val view = holder.uBinding

        view.clBandsListItem.setOnClickListener {
            callback.onItemClick(item)
        }

    }


    inner class RecyclerViewHolder(val uBinding: ItemBandsListBinding) :
        RecyclerView.ViewHolder(uBinding.root)
}