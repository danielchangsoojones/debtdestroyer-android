package com.debtdestroyer.android.ui.userinfo

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentPhoneNumberBinding
import com.debtdestroyer.android.databinding.FragmentUserInfoBinding
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.ui.trivia.TriviaActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserInfoBinding
        get() = FragmentUserInfoBinding::inflate
    private val viewModel: UserInfoVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupObserver()

    }

    private fun setupObserver() {
        viewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
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