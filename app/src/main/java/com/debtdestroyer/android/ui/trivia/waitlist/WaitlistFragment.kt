package com.debtdestroyer.android.ui.trivia.waitlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentWaitlistBinding
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim
import com.debtdestroyer.android.ui.base.hideProgressBar
import com.debtdestroyer.android.ui.base.showProgressBar
import com.debtdestroyer.android.ui.base.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaitlistFragment : BaseFragmentNoAnim<FragmentWaitlistBinding>() {
    private val viewModel: WaitlistVM by activityViewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWaitlistBinding
        get() = FragmentWaitlistBinding::inflate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setupUI()
    }

    private fun setupUI() {

    }

    private fun setupObservers() {
        setupShowEarningsObserver()
        setupQuizDataObserver()
    }

    private fun setupQuizDataObserver() {

        viewModel.res.observe(this) {
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
        viewModel.showEarnings.observe(this) {
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
