package com.debtdestroyer.android.ui.onboarding.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.debtdestroyer.android.databinding.FragmentOnBoardingBinding
import com.debtdestroyer.android.ui.base.BaseDialogFragment
import com.debtdestroyer.android.ui.base.getUrlFromIntent
import com.debtdestroyer.android.ui.base.navigateTo
import com.debtdestroyer.android.ui.onboarding.login.LoginFragmentDirections
import com.debtdestroyer.android.ui.trivia.TriviaActivity
import com.parse.ParseUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : BaseDialogFragment<FragmentOnBoardingBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnBoardingBinding
        get() = FragmentOnBoardingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = ParseUser.getCurrentUser()
        if (user != null) {
            startActivity(Intent(requireContext(), TriviaActivity::class.java))
            activity?.finish()
            //navigateTo(OnBoardingFragmentDirections.actionOnBoardingFragmentToHomeFragment())
            return
        }

        binding.termsTextView.setOnClickListener {
            getUrlFromIntent("http://www.google.com")
        }

        binding.actionLogin.setOnClickListener {
            navigateTo(OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment())
        }

        binding.actionSignup.setOnClickListener {
            navigateTo(OnBoardingFragmentDirections.actionOnBoardingFragmentToSignupFragment())
        }
    }

}