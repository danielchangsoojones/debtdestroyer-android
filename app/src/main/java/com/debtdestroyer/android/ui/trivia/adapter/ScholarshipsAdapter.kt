package com.debtdestroyer.android.ui.trivia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.debtdestroyer.android.databinding.ItemScholarshipBinding

import com.debtdestroyer.android.model.ScholarshipModel


class ScholarshipsAdapter(
    private val onItemClick: (ScholarshipModel) -> Unit
) :
    ListAdapter<ScholarshipModel, ScholarshipsAdapter.ResultsViewHolder>(
        object : DiffUtil.ItemCallback<ScholarshipModel>() {
            override fun areItemsTheSame(
                oldItem: ScholarshipModel,
                newItem: ScholarshipModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ScholarshipModel,
                newItem: ScholarshipModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    class ResultsViewHolder(private val binding: ItemScholarshipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: ScholarshipModel,
            index: Int,
            onItemClick: (ScholarshipModel) -> Unit
        ) {
            binding.apply {
                data = item
                constraintLayout.setOnClickListener { onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding =
            ItemScholarshipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(getItem(position), position, onItemClick)
    }
}