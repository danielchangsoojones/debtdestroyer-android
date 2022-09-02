package com.debtdestroyer.android.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.ui.base.BaseDialogFragment
import com.debtdestroyer.android.databinding.FragmentOnBoardingBinding
import com.debtdestroyer.android.ui.base.navigateTo


class OnBoardingFragment : BaseDialogFragment<FragmentOnBoardingBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnBoardingBinding
        get() = FragmentOnBoardingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            navigateTo(OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment())
        }
    }

}