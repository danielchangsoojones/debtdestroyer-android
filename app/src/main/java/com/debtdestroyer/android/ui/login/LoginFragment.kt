package com.debtdestroyer.android.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.FragmentLoginBinding
import com.debtdestroyer.android.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actionLogin.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_BoardingFragment)
        }
        binding.actionSignup.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_BoardingFragment)
        }
    }


}