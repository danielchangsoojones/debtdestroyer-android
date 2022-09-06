package com.debtdestroyer.android.ui.login

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.FragmentLoginBinding
import com.debtdestroyer.android.ui.base.BaseFragment
import com.debtdestroyer.android.ui.base.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launch {
            binding.actionForgotPassword.setOnClickListener {
                Timber.e("Text ${binding.signInEmailInputEditText.text.toString()}")

            }
        }

        binding.actionNext.setOnClickListener {
            navigateTo(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }


}