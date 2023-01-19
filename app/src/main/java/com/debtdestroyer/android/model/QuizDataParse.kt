package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ktx.putOrIgnore
import javax.inject.Inject

@ParseClassName("QuizData")
class QuizDataParse @Inject constructor() : SuperParseObject() {

    var question: String?
        get() = get(KEY_QUESTION).toString()
        set(value) = putOrIgnore(KEY_QUESTION, value)

    var correctAnswerIndex: String?
        get() = get(KEY_CORRECT_ANSWER_INDEX).toString()
        set(value) = putOrIgnore(KEY_CORRECT_ANSWER_INDEX, value)

    var videoUrlString: String?
        get() = get(KEY_VIDEO_URL_STRING).toString()
        set(value) = putOrIgnore(KEY_VIDEO_URL_STRING, value)

    var order: Int?
        get() = get(User.KEY_IS_DELETED) as Int?
        set(value) = putOrIgnore(KEY_ORDER, value)

    var startQuestionPromptSeconds: Any?
        get() = get(KEY_START_QUESTION_PROMPT_SECONDS)
        set(value) = putOrIgnore(KEY_START_QUESTION_PROMPT_SECONDS, value)

    var answers: List<*>
        get() = get(KEY_ANSWERS) as List<*>
        set(value) = putOrIgnore(KEY_ANSWERS, value)

    var quizTopic: QuizTopicParse
        get() = get(KEY_QUIZ_TOPIC) as QuizTopicParse
        set(value) = putOrIgnore(KEY_ANSWERS, value)

    companion object {
        const val KEY_ANSWERS = "answers"

        const val KEY_QUESTION = "question"
        const val KEY_CORRECT_ANSWER_INDEX = "correct_answer_index"
        const val KEY_QUIZ_TOPIC = "quizTopic"
        const val KEY_START_QUESTION_PROMPT_SECONDS = "start_question_prompt_seconds"
        const val KEY_VIDEO_URL_STRING = "video_url_string"

        const val KEY_VIDEO_ANSWER = "videoAnswer"
    }

    override fun toString(): String {
        return "QuizDataParse(question=$question, correctAnswerIndex=$correctAnswerIndex, videoUrlString=$videoUrlString, order=$order, startQuestionPromptSeconds=$startQuestionPromptSeconds, answers=$answers, quizTopic=$quizTopic)"
    }


}