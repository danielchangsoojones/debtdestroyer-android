package com.debtdestroyer.android.ui.trivia.daily

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentDailyBinding
import com.debtdestroyer.android.model.QuizDataParse
import com.debtdestroyer.android.model.User
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.utils.DAILY_TRIVIA_URL
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class DailyFragment : BaseFragmentNoAnim<FragmentDailyBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDailyBinding
        get() = FragmentDailyBinding::inflate

    private val viewModel: DailyVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        shouldShowEarningObserver()
        quizDataObserver()
    }

    private fun shouldShowEarningObserver() {
        viewModel.loadShouldShowEarnings()

        viewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    User.KEY_SHOULD_SHOW_EARNINGS = it.data!!
                    ///if (ParseUser.getCurrentUser()?.isAuthenticated ?? false) {
                    viewModel.getDemoQuizData()
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

    private fun quizDataObserver() {
        viewModel.quiz.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { quizList ->
                        if (quizList.isNotEmpty()) {
                            updateUI(quizList.first())
                        }
                    }

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

    private fun updateUI(qdp: QuizDataParse) {
        val firstQuizTopic = qdp.quizTopic
        binding.titleBottomTextView.text = firstQuizTopic.name
        binding.subTitleBottomTextView.text = firstQuizTopic.ticker

        val prizeAmount = firstQuizTopic.prize_amount / 100
        val prizeAmountStr = "$prizeAmount"
        binding.actionPrice.text = prizeAmountStr

        val apiDate = firstQuizTopic.start_time
        binding.dateTextView.text = firstQuizTopic.start_time.iso

        Timber.e("API Date :: $apiDate")
    }

    private fun setupUI() {
        setupVideoPlayer()
        setupVideoUrlInLoop()
    }

    private fun setupVideoPlayer() {
        val uri: Uri = Uri.parse(DAILY_TRIVIA_URL)
        binding.videoView.setVideoURI(uri)
        binding.videoView.setMediaController(null)
        setupVideoUrlInLoop()
    }

    private fun setupVideoUrlInLoop() {
        binding.videoView.start()
        binding.videoView.setOnPreparedListener {
            binding.videoView.start()
            val videoRatio = it.videoWidth / it.videoHeight.toFloat()
            val screenRatio = binding.videoView.width / binding.videoView.height.toFloat()
            val scaleX = videoRatio / screenRatio
            if (scaleX >= 1f) {
                binding.videoView.scaleX = scaleX
            } else {
                binding.videoView.scaleY = 1f / scaleX
            }
        }

        binding.videoView.setOnCompletionListener { binding.videoView.start() }
    }
}