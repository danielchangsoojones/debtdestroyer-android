package com.debtdestroyer.android.ui.trivia.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentTriviaBinding
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.ui.trivia.TriviaVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartTriviaFragment : BaseFragmentNoAnim<FragmentTriviaBinding>() {
    private val viewModel: TriviaVM by activityViewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTriviaBinding
        get() = FragmentTriviaBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupUI()
        setupObservers()

    }

    private fun setupUI() {
        binding.actionStartTrivia.setOnClickListener {
            //navigateTo(TriviaFragmentDirections.actionOnTriviaFragmentToQuizFragment())
        }
    }

    private fun setupObservers() {
        setupShowEarningsObserver()
        setupQuizDataObserver()
    }

    private fun setupQuizDataObserver() {

        viewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
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

    private fun setupShowEarningsObserver() {
        viewModel.showEarnings.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
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
}
