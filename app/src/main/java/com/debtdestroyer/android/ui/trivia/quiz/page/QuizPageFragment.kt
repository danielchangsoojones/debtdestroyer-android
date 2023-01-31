package com.debtdestroyer.android.ui.trivia.quiz.page

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.FragmentQuizOptionsBinding
import com.debtdestroyer.android.model.QuizDataParse
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class QuizPageFragment(var quizDataParse: QuizDataParse, var position: Int, var total: Int) :
    BaseFragmentNoAnim<FragmentQuizOptionsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuizOptionsBinding
        get() = FragmentQuizOptionsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("Quiz data:: $quizDataParse")
        setUIContent()
    }

    private fun setUIContent() {
        val questionOutOfTotal = getString(R.string.question_2_of_10, position, total)

        binding.headingTextview.text = "${quizDataParse.question}"
        binding.titleTextView.text = questionOutOfTotal

        if (!quizDataParse.videoUrlString.isNullOrEmpty())
            setupVideoPlayer(quizDataParse.videoUrlString!!)
    }

    private fun setupVideoPlayer(url: String) {
        val uri: Uri = Uri.parse(url)
        binding.videoView.setVideoURI(uri)
        binding.videoView.setMediaController(null)

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
    }
}