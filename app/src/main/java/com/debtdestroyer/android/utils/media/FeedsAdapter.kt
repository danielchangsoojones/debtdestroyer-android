package com.debtdestroyer.android.utils.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.debtdestroyer.android.databinding.ItemFeedBinding
import com.debtdestroyer.android.model.MediaObject


class FeedsAdapter(
    private val onItemClick: (MediaObject) -> Unit,
) : ListAdapter<MediaObject, PlayerViewHolder>(
    object : DiffUtil.ItemCallback<MediaObject>() {
        override fun areItemsTheSame(oldItem: MediaObject, newItem: MediaObject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MediaObject, newItem: MediaObject): Boolean {
            return oldItem.id == newItem.id
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