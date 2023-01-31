package com.debtdestroyer.android.ui.trivia.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentQuizBinding
import com.debtdestroyer.android.model.QuizDataParse
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim
import com.debtdestroyer.android.ui.base.hideProgressBar
import com.debtdestroyer.android.ui.base.showProgressBar
import com.debtdestroyer.android.ui.base.showToast
import com.debtdestroyer.android.ui.trivia.quiz.page.QuizPageFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class QuizFragment : BaseFragmentNoAnim<FragmentQuizBinding>() {

    private val viewModel: QuizVM by viewModels()
    private var mQuizList: ArrayList<QuizDataParse> = arrayListOf()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuizBinding
        get() = FragmentQuizBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizDataObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.load()
    }

    private fun quizDataObserver() {
        Timber.e("QuizFragment:: quizDataObserver")
        viewModel.res.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { quizList ->
                        Timber.e("QuizFragment:: quizList=${quizList.size}")

                        NUM_PAGES = quizList.size
                        updateAdapter(quizList)
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

    private fun updateAdapter(quizList: ArrayList<QuizDataParse>) {
        if (quizList.isEmpty())
            return
        Timber.e("QuizFragment:: updateAdapter=${quizList.size}")
        quizList.sortBy {
            it.order
        }
        this.mQuizList = quizList
        binding.pager.adapter = QuizSlidePagerAdapter(this)
    }

    companion object {
        private var NUM_PAGES = 0
    }

    private inner class QuizSlidePagerAdapter(fa: Fragment) :
        FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = QuizPageFragment(mQuizList[position], position, NUM_PAGES)
    }
}