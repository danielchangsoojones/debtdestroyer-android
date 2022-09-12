package com.debtdestroyer.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.debtdestroyer.android.databinding.ItemConnectedAccountsBinding
import com.debtdestroyer.android.model.Accounts


class AccountsAdapter(
    private val onItemClick: (Accounts, Int) -> Unit
) :
    ListAdapter<Accounts, AccountsAdapter.ResultsViewHolder>(
        object : DiffUtil.ItemCallback<Accounts>() {
            override fun areItemsTheSame(oldItem: Accounts, newItem: Accounts): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Accounts, newItem: Accounts): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    class ResultsViewHolder(private val binding: ItemConnectedAccountsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: Accounts,
            index: Int,
            onItemClick: (Accounts, Int) -> Unit
        ) {
            binding.apply {
                item = data
                binding.bankImageView.setImageResource(
                    data.resId
                )
                root.setOnClickListener { onItemClick(data, index) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding =
            ItemConnectedAccountsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(getItem(position), position, onItemClick)
    }
}