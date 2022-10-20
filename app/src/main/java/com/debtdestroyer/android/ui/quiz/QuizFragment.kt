package com.debtdestroyer.android.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import com.debtdestroyer.android.databinding.FragmentQuizOptionsBinding
import com.debtdestroyer.android.ui.base.BaseFragment


class QuizFragment : BaseFragment<FragmentQuizOptionsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuizOptionsBinding
        get() = FragmentQuizOptionsBinding::inflate

}