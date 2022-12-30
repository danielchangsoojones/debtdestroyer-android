package com.debtdestroyer.android.ui.trivia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.debtdestroyer.android.databinding.ItemSettingsBinding
import com.debtdestroyer.android.model.DataSettings


class SettingsAdapter(
    private val onItemClick: (DataSettings, Int) -> Unit
) :
    ListAdapter<DataSettings, SettingsAdapter.ResultsViewHolder>(
        object : DiffUtil.ItemCallback<DataSettings>() {
            override fun areItemsTheSame(oldItem: DataSettings, newItem: DataSettings): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataSettings, newItem: DataSettings): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    class ResultsViewHolder(private val binding: ItemSettingsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: DataSettings,
            index: Int,
            onItemClick: (DataSettings, Int) -> Unit
        ) {
            binding.apply {
                item = data
                val icon = getDrawable(binding.root.context, data.resId)
                binding.itemTitleTextview.setCompoundDrawablesWithIntrinsicBounds(
                    icon,
                    null,
                    null,
                    null
                )
                root.setOnClickListener { onItemClick(data, index) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding =
            ItemSettingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(getItem(position), position, onItemClick)
    }
}