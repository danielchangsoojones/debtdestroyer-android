package com.debtdestroyer.android.ui.dob

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentDobBinding
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.ui.trivia.TriviaActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DOBFragment : BaseFragment<FragmentDobBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDobBinding
        get() = FragmentDobBinding::inflate

    private val viewModel: DobVM by viewModels()
    //signup
    // {"objectId":"HqNl0PBrea","createdAt":"2023-01-31T13:23:26.285Z","sessionToken":"r:069cfb740ea2013a57aa764445192db2"}
    //phone
    //{"updatedAt":"2023-01-31T13:24:34.129Z"}
    //

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
                    startActivity(Intent(requireContext(), TriviaActivity::class.java))
                    activity?.finish()
                }
                Status.ERROR -> {
                    hideProgressBar()
                    showToast(getString(R.string.invalid_input), it.message)
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