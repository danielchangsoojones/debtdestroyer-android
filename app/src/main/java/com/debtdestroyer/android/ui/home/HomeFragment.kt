package com.debtdestroyer.android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.debtdestroyer.android.callback.Params
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentHomeBinding
import com.debtdestroyer.android.model.MediaObject
import com.debtdestroyer.android.model.SweepParse
import com.debtdestroyer.android.model.TransactionParse
import com.debtdestroyer.android.model.WinnersParse
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim
import com.debtdestroyer.android.ui.base.hideProgressBar
import com.debtdestroyer.android.ui.base.showError
import com.debtdestroyer.android.ui.base.showProgressBar
import com.debtdestroyer.android.utils.media.WinnersAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragmentNoAnim<FragmentHomeBinding>() {

    private val mediaObjectList = ArrayList<MediaObject>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupUI()
        setupObservers()
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
        viewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val map = it.data
                    handleSavingsResponse(map)
                    hideProgressBar()
                }
                Status.ERROR -> {
                    hideProgressBar()
                    showError(it.message)
                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setUpSweepStackObserver() {
        viewModel.sweepRes.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val map = it.data
                    handleSweepStackResponse(map)
                    hideProgressBar()
                }
                Status.ERROR -> {
                    hideProgressBar()
                    showError(it.message)
                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setUpWinnersObserver() {
        viewModel.winners.observe(viewLifecycleOwner) {
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
                    showError(it.message)
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

    private fun handleWinnersResponse(list: ArrayList<WinnersParse>) {

        list.forEach { itw ->
            Timber.e("The WINNER is $itw")
        }
        /*
        val mAdapter: WinnersAdapter
        binding.exoPlayerRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter = WinnersAdapter(onItemClick = {
                //Click
            })
            adapter = mAdapter
        }
        mAdapter.submitList(list.toMutableList())*/
    }

    private fun setPlayerList() {
        prepareVideoList()
        val mAdapter: WinnersAdapter
        binding.exoPlayerRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter = WinnersAdapter(onItemClick = {
                //Click
            })
            adapter = mAdapter
        }
        //mAdapter.submitList(mediaObjectList.toMutableList())
    }

    override fun onDestroy() {
        //binding.exoPlayerRecyclerView.releasePlayer()
        super.onDestroy()
    }

    private fun prepareVideoList() {
        val mediaObject = MediaObject()
        mediaObject.id = 1
        mediaObject.userHandle = "User 1"
        mediaObject.title = "Item 1"
        mediaObject.coverUrl =
            "https://kamdora.com/wp-content/uploads/2018/06/20-topic-ideas-to-write-on-as-a-blogger-on-kamdora-2018.jpg"
        mediaObject.url =
            "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4"

        val mediaObject2 = MediaObject()
        mediaObject2.id = 2
        mediaObject2.userHandle = "user 2"
        mediaObject2.title = "Item 2"
        mediaObject2.coverUrl =
            "https://blog.hubspot.com/hubfs/what-is-a-blog-2.jpg"
        mediaObject2.url =
            "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4"

        val mediaObject3 = MediaObject()
        mediaObject3.id = 3
        mediaObject3.userHandle = "User 3"
        mediaObject3.title = "Item 3"
        mediaObject3.coverUrl =
            "https://kamdora.com/wp-content/uploads/2018/06/20-topic-ideas-to-write-on-as-a-blogger-on-kamdora-2018.jpg"
        mediaObject3.url =
            "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4"

        val mediaObject4 = MediaObject()
        mediaObject4.id = 4
        mediaObject4.userHandle = "User 4"
        mediaObject4.title = "Item 4"
        mediaObject4.coverUrl =
            "https://blog.hubspot.com/hubfs/what-is-a-blog-2.jpg"
        mediaObject4.url =
            "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4"

        val mediaObject5 = MediaObject()
        mediaObject5.id = 5
        mediaObject5.userHandle = "User 5"
        mediaObject5.title = "Item 5"
        mediaObject5.coverUrl =
            "https://blog.hubspot.com/hubfs/what-is-a-blog-2.jpg"
        mediaObject5.url =
            "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4"

        mediaObjectList.add(mediaObject)
        mediaObjectList.add(mediaObject2)
        mediaObjectList.add(mediaObject3)
        mediaObjectList.add(mediaObject4)
        mediaObjectList.add(mediaObject5)
    }
}