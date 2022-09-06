package com.debtdestroyer.android.ui.phonenumber

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.debtdestroyer.android.databinding.FragmentPhoneNumberBinding
import com.debtdestroyer.android.ui.base.BaseFragment
import com.debtdestroyer.android.ui.base.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class PhoneNumberFragment : BaseFragment<FragmentPhoneNumberBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPhoneNumberBinding
        get() = FragmentPhoneNumberBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            binding.phoneNumberInputEditText.addTextChangedListener(PhoneNumberFormattingTextWatcher())
            delay(10000)
            Timber.e("CALLE ${binding.phoneNumberInputEditText.text.toString()}")
        }
        binding.actionNext.setOnClickListener {
            navigateTo(PhoneNumberFragmentDirections.actionPhoneNumberFragmentToHomeFragment())
        }
    }

}