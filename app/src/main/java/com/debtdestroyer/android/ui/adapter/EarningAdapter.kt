package com.debtdestroyer.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.debtdestroyer.android.databinding.ItemConnectedAccountsBinding
import com.debtdestroyer.android.databinding.ItemEarnTicketsBinding
import com.debtdestroyer.android.model.Accounts


class EarningAdapter(
    private val onItemClick: (Boolean) -> Unit
) :
    ListAdapter<String, EarningAdapter.ResultsViewHolder>(
        object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    class ResultsViewHolder(private val binding: ItemEarnTicketsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: String,
            index: Int,
            onItemClick: (Boolean) -> Unit
        ) {
            /*binding.apply {
                item = data
                binding.bankImageView.setImageResource(
                    data.resId
                )
                root.setOnClickListener { onItemClick(data, index) }
            }*/
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding =
            ItemEarnTicketsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        //holder.bind(getItem(position), position, onItemClick)
    }
}