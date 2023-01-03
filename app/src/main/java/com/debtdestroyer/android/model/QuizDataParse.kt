package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("QuizData")
data class QuizDataParse(
    var answers: List<String> = emptyList(),
    var question: String,
    var video_url_string: String,
    var order: Int,
    var start_question_prompt_seconds: Int,
    var correct_answer_index: Int,
    var quizTopic: QuizTopicParse,
    var createdAt: String,
    var updatedAt: String) : ParseObject()