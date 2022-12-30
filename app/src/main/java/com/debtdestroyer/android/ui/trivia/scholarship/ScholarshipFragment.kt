package com.debtdestroyer.android.ui.trivia.scholarship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.debtdestroyer.android.callback.Params
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentHomeBinding
import com.debtdestroyer.android.databinding.FragmentScholarshipBinding
import com.debtdestroyer.android.model.MediaObject
import com.debtdestroyer.android.model.SweepParse
import com.debtdestroyer.android.model.TransactionParse
import com.debtdestroyer.android.model.WinnersParse
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.ui.trivia.adapter.ScholarshipsAdapter
import com.debtdestroyer.android.utils.JsonUtils
import com.debtdestroyer.android.utils.getListOfItems
import com.debtdestroyer.android.utils.media.WinnersAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ScholarshipFragment : BaseFragmentNoAnim<FragmentScholarshipBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentScholarshipBinding
        get() = FragmentScholarshipBinding::inflate

    private val viewModel: ScholarshipVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupObservers()
    }

    private fun setupObservers() {
        val listOfScholarships = getListOfItems(requireActivity())
        val mAdapter = ScholarshipsAdapter(onItemClick = { data ->
            getUrlFromIntent(data.url)
        })
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mAdapter.submitList(listOfScholarships)
    }

}