package com.debtdestroyer.android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentTriviaBinding
import com.debtdestroyer.android.ui.base.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriviaFragment : BaseFragmentNoAnim<FragmentTriviaBinding>() {
    private val viewModel: TriviaHomeVM by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTriviaBinding
        get() = FragmentTriviaBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.viewModel = viewModel
        setupUI()
        setupObservers()

    }

    private fun setupUI() {
        binding.actionStartTrivia.setOnClickListener {
            viewModel.load()
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
                    navigateTo(TriviaFragmentDirections.actionOnTriviaFragmentToQuizFragment())
                }
                Status.ERROR -> {
                    hideProgressBar()
                    showError(it.message)
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
                    showError(it.message)
                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }
}
