package com.debtdestroyer.android.ui.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentSignupBinding
import com.debtdestroyer.android.ui.base.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate

    private val viewModel: SignupVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }

    private fun setupObserver() {
        viewModel.res.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    navigateTo(SignupFragmentDirections.actionSignupFragmentToPhoneNumberFragment())
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