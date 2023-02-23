package com.debtdestroyer.android.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.debtdestroyer.android.databinding.FragmentTaskBinding
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.utils.emojireactionlibrary.ClickInterface
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskFragment : BaseFragment<FragmentTaskBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTaskBinding
        get() = FragmentTaskBinding::inflate

    private val viewModel: TaskVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setupObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setClickListener()
    }

    private fun setClickListener() {
        binding.reactionView.setOnEmojiClickListener(object : ClickInterface {
            override fun onEmojiClicked(emojiIndex: Int, x: Int, y: Int) {
                // emojiIndex is the index of the emoji being clicked (0 based)
                // x,y are the coordinates of the clicked position relative to the image
                // (if x && y == -1 => changed by program(SetClickedEmojiNumber)
            }

            override fun onEmojiUnclicked(emojiIndex: Int, x: Int, y: Int) {
                // emojiIndex is the index of the emoji being clicked (0 based)
                // x,y are the coordinates of the clicked position relative to the image
                // (if x && y == -1 => changed by program(SetClickedEmojiNumber)
            }
        })
    }
}