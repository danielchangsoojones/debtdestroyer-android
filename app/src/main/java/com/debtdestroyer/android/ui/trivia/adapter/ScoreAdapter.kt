package com.debtdestroyer.android.ui.trivia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.debtdestroyer.android.databinding.ItemListScoreBinding


class ScoreAdapter(
    private val onItemClick: (String) -> Unit
) :
    ListAdapter<String, ScoreAdapter.ResultsViewHolder>(
        object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    class ResultsViewHolder(private val binding: ItemListScoreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            name: String,
            index: Int,
            onItemClick: (String) -> Unit
        ) {
            binding.apply {
                nameTextView.text = name
                root.setOnClickListener { onItemClick(name) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding =
            ItemListScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(getItem(position), position, onItemClick)
    }
}
