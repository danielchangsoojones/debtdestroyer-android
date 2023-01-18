package com.debtdestroyer.android.model

import com.parse.ParseClassName

@ParseClassName("QuizData")
data class QuizDataParse(
    var answers: List<String> = emptyList(),
    var question: String = "",
    var video_url_string: String = "",
    var order: Int = 0,
    var start_question_prompt_seconds: Int = 0,
    var correct_answer_index: Int = 0,
    var quizTopic: QuizTopicParse
) : SuperParseObject() {

    override fun toString(): String {
        return "QuizDataParse(answers=$answers, question='$question', video_url_string='$video_url_string', order=$order, start_question_prompt_seconds=$start_question_prompt_seconds, correct_answer_index=$correct_answer_index, quizTopic=$quizTopic)"
    }
}