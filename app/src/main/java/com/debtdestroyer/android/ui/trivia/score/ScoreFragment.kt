package com.debtdestroyer.android.ui.trivia.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentScoreBinding
import com.debtdestroyer.android.ui.trivia.adapter.ScoreAdapter
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim
import com.debtdestroyer.android.ui.base.hideProgressBar
import com.debtdestroyer.android.ui.base.showProgressBar
import com.debtdestroyer.android.ui.base.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ScoreFragment : BaseFragmentNoAnim<FragmentScoreBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentScoreBinding
        get() = FragmentScoreBinding::inflate

    private val viewModel: ScoreVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getLeaderboardData()

        viewModel.quiz.observe(this){
            when (it.status) {
                Status.SUCCESS -> {
                    Timber.e("RESULT IN OBSERVER :: ${it.data}")
                    hideProgressBar()
                }
                Status.ERROR -> {
                    hideProgressBar()
                    showToast(getString(R.string.invalid_input), it.message)
                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
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