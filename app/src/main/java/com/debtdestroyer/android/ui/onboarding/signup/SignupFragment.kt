package com.debtdestroyer.android.ui.onboarding.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentLoginBinding
import com.debtdestroyer.android.databinding.FragmentSignupBinding
import com.debtdestroyer.android.model.User
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.ui.onboarding.login.LoginFragmentDirections
import com.debtdestroyer.android.ui.onboarding.login.LoginVM
import com.parse.ParseUser
import com.parse.SignUpCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate

    private val viewModel: SignupVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupObserver()
        /*       binding.actionNext.setOnClickListener {
            val parseUser = User()
            parseUser.username = "priyanka@hotmail.com"
            parseUser.email = "priyanka@hotmail.com"
            parseUser.setPassword("p4Ve1y77")
            parseUser.deviceType = "android"

            parseUser.signUpInBackground {
                if (it != null)
                    Timber.e("Signup ${it.localizedMessage}")
                else {
                    Timber.e("Signup succcesssss")
                }
            }
        }*/
    }

    private fun setupObserver() {
        viewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    navigateTo(SignupFragmentDirections.actionSignupFragmentToPhoneNumberFragment())
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