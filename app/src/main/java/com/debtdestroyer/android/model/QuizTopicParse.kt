package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("QuizTopic")
data class QuizTopicParse(
    var prize_amount: Int,
    var name: String,
    var ticker: String,
    var start_time: StartTime,
    var current_time_seconds: Int,
    var createdAt: String,
    var updatedAt: String) : ParseObject()

data class StartTime(var iso: String)