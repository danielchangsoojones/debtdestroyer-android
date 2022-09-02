package com.debtdestroyer.android.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.FragmentLoginBinding
import com.debtdestroyer.android.databinding.FragmentSignupBinding
import com.debtdestroyer.android.ui.base.BaseFragment
import com.debtdestroyer.android.ui.base.navigateTo
import kotlinx.coroutines.launch

class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            binding.actionNext.setOnClickListener {
                navigateTo(SignupFragmentDirections.actionSignupFragmentToPhoneNumberFragment())
            }
        }
    }


}