package com.debtdestroyer.android.ui.phonenumber

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentPhoneNumberBinding
import com.debtdestroyer.android.ui.base.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneNumberFragment : BaseFragment<FragmentPhoneNumberBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPhoneNumberBinding
        get() = FragmentPhoneNumberBinding::inflate
    private val viewModel: PhoneNumberVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneNumberInputEditText.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.viewModel = viewModel

        setupObserver()

    }

    private fun setupObserver() {
        viewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    navigateTo(PhoneNumberFragmentDirections.actionPhoneNumberFragmentToHomeFragment())
                }
                Status.ERROR -> {
                    showError(it.message)
                    hideProgressBar()
                }
                Status.LOADING -> {
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