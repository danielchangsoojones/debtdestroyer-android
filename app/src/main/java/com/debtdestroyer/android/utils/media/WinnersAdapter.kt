package com.debtdestroyer.android.utils.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.debtdestroyer.android.databinding.ItemFeedBinding
import com.debtdestroyer.android.model.WinnersParse


class WinnersAdapter(
    private val onItemClick: (WinnersParse) -> Unit,
) : ListAdapter<WinnersParse, PlayerViewHolder>(
    object : DiffUtil.ItemCallback<WinnersParse>() {
        override fun areItemsTheSame(oldItem: WinnersParse, newItem: WinnersParse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WinnersParse, newItem: WinnersParse): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding, parent)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(getItem(position), position, onItemClick)
    }
}