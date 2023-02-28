package com.debtdestroyer.android.ui.trivia.quiz.page

import android.content.Context
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatRadioButton
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.FragmentQuizOptionsBinding
import com.debtdestroyer.android.databinding.LayoutRadioButtonBinding
import com.debtdestroyer.android.model.QuizDataParse
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim
import com.debtdestroyer.android.ui.base.showToast
import com.debtdestroyer.android.utils.radio.RadioGridGroup
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class QuizPageFragment(var quizDataParse: QuizDataParse, var position: Int, var total: Int) :
    BaseFragmentNoAnim<FragmentQuizOptionsBinding>(), RadioGridGroup.OnCheckedChangeListener {

    //countdown
    private var countdown_timer: CountDownTimer? = null
    private var time_in_milliseconds = 30000L
    private var pauseOffSet = 0L
    private var correctAnswer = ""
    private var correctAnswerID = 0
    lateinit var correctAnswerButton :AppCompatRadioButton

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuizOptionsBinding
        get() = FragmentQuizOptionsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUIContent()
    }

    private fun setUIContent() {
        setQuizContent()
        setPlayerContent()
        setupRadioOptions()
        setCounterOnProgress()
        updateAudioVolume()

    }

    private fun updateAudioVolume() {
        if (isMute()) {
            binding.muteIv.setImageResource(R.drawable.ic_state_mute)
        } else
            binding.muteIv.setImageResource(R.drawable.ic_state_unmute)

        binding.muteIv.setOnClickListener {
            updateMuteState(!isMute())
            if (isMute()) {
                binding.muteIv.setImageResource(R.drawable.ic_state_mute)
            } else
                binding.muteIv.setImageResource(R.drawable.ic_state_unmute)
        }
    }

    private fun setQuizContent() {
        val questionOutOfTotal = getString(R.string.question_2_of_10, (position + 1), total)
        binding.headingTextview.text = "${quizDataParse.question}"
        binding.titleTextView.text = questionOutOfTotal
    }

    private fun setPlayerContent() {
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

    private fun setupRadioOptions() {
        binding.gridRadioGrp.removeAllViews()
        quizDataParse.correctAnswerIndex?.let {
            correctAnswer = quizDataParse.answers[it.toInt()] as String
        }

        quizDataParse.answers.forEach {
            val option = it as String
            val customRadio = getRadioButton(option)
            binding.gridRadioGrp.addView(customRadio.root)
        }

        binding.gridRadioGrp.setOnCheckedChangeListener(this)
    }

    private fun getRadioButton(option: String): LayoutRadioButtonBinding {
        val radioBinding = LayoutRadioButtonBinding.inflate(layoutInflater, binding.gridRadioGrp, false)
        radioBinding.radioButton.id = View.generateViewId()
        radioBinding.radioButton.text = option
        radioBinding.radioButton.isChecked = false

        if (option.equals(correctAnswer, ignoreCase = true)) {
            correctAnswerButton = radioBinding.radioButton
            correctAnswerID = radioBinding.radioButton.id
        }

        return radioBinding
    }

    //Count Down
    private fun setCounterOnProgress() {
        resetTimer()
        starTimer(pauseOffSet)
        binding.linearProgressIndicator.max = (time_in_milliseconds / 1000).toInt()
    }

    private fun starTimer(pauseOffSetL: Long) {
        countdown_timer = object : CountDownTimer(time_in_milliseconds - pauseOffSetL, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffSet = time_in_milliseconds - millisUntilFinished
                val timeLeft = (millisUntilFinished / 1000).toInt()

                binding.linearProgressIndicator.setProgressCompat(timeLeft, true)
                binding.countdownTextView.text = getString(R.string.seconds_left, timeLeft)
            }

            override fun onFinish() {
                showToast("Time out!!", "Finish")
            }
        }.start()
    }

    private fun pauseTimer() {
        if (countdown_timer != null) {
            countdown_timer!!.cancel()
        }
    }

    private fun resetTimer() {
        if (countdown_timer != null) {
            countdown_timer!!.cancel()
            countdown_timer = null
            pauseOffSet = 0
        }
        correctAnswer = ""
        correctAnswerID = 0
    }

    override fun onDestroyView() {
        resetTimer()
        super.onDestroyView()
    }

    override fun onCheckedChanged(group: RadioGridGroup?, checkedId: Int) {
        correctAnswerButton.setBackgroundResource(R.drawable.bg_button_green_rounded)
        if (checkedId != correctAnswerID){
            //group.sele.setBackgroundResource(R.drawable.bg_button_green_rounded)
        }
    }
    /**
     * To Mute or Un-mute using Audio Manager
     */
    private fun updateMuteState(mute: Boolean) {
        activity?.let {
            getAudioManager(it).adjustStreamVolume(AudioManager.STREAM_MUSIC, if (mute) -100 else 100, 0)

        }
    }

    /**
     * Return true -> audio player is in mute state, false otherwise
     */
    private fun isMute(): Boolean {
        activity?.let {
            getAudioManager(it).getStreamVolume(AudioManager.STREAM_MUSIC).let { volume ->
                return volume == 0
            }
        }
        return false
    }

    /**
     * To Get the Audio Manager
     */
    private fun getAudioManager(context: Context) = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
}