package com.debtdestroyer.android.ui.trivia.scholarship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.debtdestroyer.android.databinding.FragmentScholarshipBinding
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.ui.trivia.adapter.ScholarshipsAdapter
import com.debtdestroyer.android.utils.getListOfItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScholarshipFragment : BaseFragmentNoAnim<FragmentScholarshipBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentScholarshipBinding
        get() = FragmentScholarshipBinding::inflate

    private val viewModel: ScholarshipVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
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