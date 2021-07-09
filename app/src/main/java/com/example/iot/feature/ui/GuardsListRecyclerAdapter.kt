package com.example.iot.feature.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.iot.R
import com.example.iot.databinding.ItemGuardsListBinding
import com.example.iot.feature.data.GuardsListItem

class GuardsListRecyclerAdapter(
    val callback: CallBack
) : ListAdapter<GuardsListItem, GuardsListRecyclerAdapter.RecyclerViewHolder>(object :
    DiffUtil.ItemCallback<GuardsListItem>() {
    override fun areItemsTheSame(oldItem: GuardsListItem, newItem: GuardsListItem): Boolean {
        return oldItem.staffId == newItem.staffId
    }

    override fun areContentsTheSame(oldItem: GuardsListItem, newItem: GuardsListItem): Boolean {
        return true

    }
}) {

    interface CallBack {
        fun onItemClick(item: GuardsListItem)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {


        val mBinding: ItemGuardsListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_guards_list,
            parent,
            false
        )

        return RecyclerViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = getItem(position)
        holder.uBinding.guard = item
        holder.itemView.tag = item
        val view = holder.uBinding
//        view.item_tv_distance.text = item.distance.toString() + " m"

        view.clGuardsListItem.setOnClickListener {
            callback.onItemClick(item)
        }

    }


    inner class RecyclerViewHolder(val uBinding: ItemGuardsListBinding) :
        RecyclerView.ViewHolder(uBinding.root)
}