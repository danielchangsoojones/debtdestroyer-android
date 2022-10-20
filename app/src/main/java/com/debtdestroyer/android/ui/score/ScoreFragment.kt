package com.debtdestroyer.android.ui.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.debtdestroyer.android.databinding.FragmentScoreBinding
import com.debtdestroyer.android.ui.adapter.ScoreAdapter
import com.debtdestroyer.android.ui.base.BaseFragment


class ScoreFragment : BaseFragment<FragmentScoreBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentScoreBinding
        get() = FragmentScoreBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mAdapter: ScoreAdapter

        binding.layoutList.recyclerList.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = ScoreAdapter(onItemClick = { name ->

            })
            adapter = mAdapter
        }

        val list = arrayListOf<String>()
        list.add("Kristen Stewart")
        list.add("John Jacob")
        list.add("Chrome mac")
        list.add("Issihi Issiac")
        list.add("Kristen Stewart")
        list.add("John Jacob")
        list.add("Chrome mac")
        list.add("Kristen Stewart")
        list.add("John Jacob")
        list.add("Chrome mac")
        list.add("Issihi Issiac")
        list.add("Kristen Stewart")
        list.add("John Jacob")
        list.add("Chrome mac")
        list.add("Issihi Issiac")
        mAdapter.submitList(list.toMutableList())
    }
}