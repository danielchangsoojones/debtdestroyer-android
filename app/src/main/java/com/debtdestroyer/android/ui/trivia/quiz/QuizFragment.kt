package com.debtdestroyer.android.ui.trivia.quiz

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.debtdestroyer.android.databinding.FragmentQuizOptionsBinding
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim


class QuizFragment : BaseFragmentNoAnim<FragmentQuizOptionsBinding>() {

    private val viewModel: QuizVM by viewModels()
    var progress = 0
    lateinit var countDownTimer: CountDownTimer
    var endTime = 250


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuizOptionsBinding
        get() = FragmentQuizOptionsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //countDownTimer =
    }

    private fun fn_countdown() {
        if (binding.etTimer.getText().toString().isNotEmpty()) {
            var myProgress = 0
            try {
                countDownTimer!!.cancel()
            } catch (e: Exception) {
            }
            val timeInterval: String = binding.etTimer.getText().toString()
            progress = 1
            endTime = timeInterval.toInt() // up to finish time
            countDownTimer = object : CountDownTimer((endTime * 1000).toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    setProgress(progress, endTime)
                    progress = progress + 1
                    val seconds = (millisUntilFinished / 1000).toInt() % 60
                    val minutes = (millisUntilFinished / (1000 * 60) % 60).toInt()
                    val hours = (millisUntilFinished / (1000 * 60 * 60) % 24).toInt()
                    val newtime = "$hours:$minutes:$seconds"
                    if (newtime == "0:0:0") {
                        binding.tvTimer.setText("00:00:00")
                    } else if (hours.toString().length == 1 && minutes.toString().length == 1 && seconds.toString().length == 1) {
                        binding.tvTimer.setText("0$hours:0$minutes:0$seconds")
                    } else if (hours.toString().length == 1 && minutes.toString().length == 1) {
                        binding.tvTimer.setText("0$hours:0$minutes:$seconds")
                    } else if (hours.toString().length == 1 && seconds.toString().length == 1) {
                        binding.tvTimer.setText("0$hours:$minutes:0$seconds")
                    } else if (minutes.toString().length == 1 && seconds.toString().length == 1) {
                        binding.tvTimer.setText("$hours:0$minutes:0$seconds")
                    } else if (hours.toString().length == 1) {
                        binding.tvTimer.setText("0$hours:$minutes:$seconds")
                    } else if (minutes.toString().length == 1) {
                        binding.tvTimer.setText("$hours:0$minutes:$seconds")
                    } else if (seconds.toString().length == 1) {
                        binding.tvTimer.setText("$hours:$minutes:0$seconds")
                    } else {
                        binding.tvTimer.setText("$hours:$minutes:$seconds")
                    }
                }

                override fun onFinish() {
                    setProgress(progress, endTime)
                }
            }
            countDownTimer.start()
        } else {
            Toast.makeText(requireContext(), "Please enter the value", Toast.LENGTH_LONG).show()
        }
    }

    fun setProgress(startTime: Int, endTime: Int) {
        /*progressBarView.setMax(endTime)
        progressBarView.setSecondaryProgress(endTime)
        progressBarView.setProgress(startTime)*/
    }
}