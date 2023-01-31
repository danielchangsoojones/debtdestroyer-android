package com.debtdestroyer.android.ui.promocode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentPromoCodeBinding
import com.debtdestroyer.android.ui.base.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromoCodeFragment : BaseFragment<FragmentPromoCodeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPromoCodeBinding
        get() = FragmentPromoCodeBinding::inflate

    private val viewModel: PromoCodeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        getArgs()
    }

    private fun getArgs() {
        val args: PromoCodeFragmentArgs by navArgs()
        viewModel.fName.postValue(args.fname)
        viewModel.lName.postValue(args.lname)
    }

    private fun setupObserver() {
        viewModel.res.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    navigateTo(PromoCodeFragmentDirections.actionPromoCodeFragmentToTriviaActivity())
                }
                Status.ERROR -> {
                    showToast(getString(R.string.invalid_input), it.message)
                    hideProgressBar()
                }
                Status.LOADING -> {
                    hideKeyBoard(binding.actionNext)
                    showProgressBar()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.res.removeObservers(this)
    }
}