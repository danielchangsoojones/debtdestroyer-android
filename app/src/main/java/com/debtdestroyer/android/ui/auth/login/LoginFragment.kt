package com.debtdestroyer.android.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.AuthResponseCallback
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentLoginBinding
import com.debtdestroyer.android.model.User
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.ui.trivia.TriviaActivity
import com.parse.LogInCallback
import com.parse.ParseUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    private val viewModel: LoginVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupObserver()
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.actionForgotPassword.setOnClickListener {
            navigateTo(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }
    }

    private fun setupObserver() {
        viewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    //navigateTo(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    startActivity(Intent(requireContext(), TriviaActivity::class.java))
                    activity?.finish()
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