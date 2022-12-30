package com.debtdestroyer.android.ui.trivia.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import com.debtdestroyer.android.databinding.FragmentQuizOptionsBinding
import com.debtdestroyer.android.ui.base.BaseFragment
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim


class QuizFragment : BaseFragmentNoAnim<FragmentQuizOptionsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuizOptionsBinding
        get() = FragmentQuizOptionsBinding::inflate

}