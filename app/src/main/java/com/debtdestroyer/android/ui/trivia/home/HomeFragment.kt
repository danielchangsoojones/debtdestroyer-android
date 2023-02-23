package com.debtdestroyer.android.ui.trivia.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Params
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentHomeBinding
import com.debtdestroyer.android.model.MediaObject
import com.debtdestroyer.android.model.SweepParse
import com.debtdestroyer.android.model.TransactionParse
import com.debtdestroyer.android.ui.base.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragmentNoAnim<FragmentHomeBinding>() {

    private val mediaObjectList = ArrayList<MediaObject>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupUI()
    }

    private fun setupObservers() {
        setUpSavingsObserver()

        setUpSweepStackObserver()

        setUpWinnersObserver()
    }

    private fun setupUI() {
        viewModel.load()
    }

    private fun setUpSavingsObserver() {
        viewModel.res.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    val map = it.data
                    handleSavingsResponse(map)
                    hideProgressBar()
                }
                Status.ERROR -> {
                    hideProgressBar()
                    showToast(getString(R.string.invalid_input), it.message)
                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setUpSweepStackObserver() {
        viewModel.sweepRes.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    val map = it.data
                    handleSweepStackResponse(map)
                    hideProgressBar()
                }
                Status.ERROR -> {
                    hideProgressBar()
                    showToast(getString(R.string.invalid_input), it.message)
                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setUpWinnersObserver() {
        viewModel.winners.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    Timber.e("The WINNER is ${it.data}")

                    it.data?.let { it1 ->
                        //handleWinnersResponse(it1)
                    }
                }
                Status.ERROR -> {
                    hideProgressBar()
                    showToast(getString(R.string.invalid_input), it.message)
                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun handleSavingsResponse(map: Map<String, *>?) {
        map?.let {
            val transactionParseData = TransactionParse()
            transactionParseData.ticketCount = map[Params.ticketCount] as Int
            transactionParseData.totalAmountPaidToLoan = map[Params.totalAmountPaidToLoan] as Int
            transactionParseData.progressMeterTitle = map[Params.progressMeterTitle] as String
            binding.savings = transactionParseData
        }
    }

    private fun handleSweepStackResponse(map: Map<String, *>?) {
        map?.let {
            val sweepParse = SweepParse()
            sweepParse.prizeAmountTxt = map[Params.prizeAmountTxt].toString()
            sweepParse.deadlineTxt = map[Params.deadlineTxt].toString()
            sweepParse.title = map[Params.title].toString()
            binding.sweep = sweepParse
        }
    }
}