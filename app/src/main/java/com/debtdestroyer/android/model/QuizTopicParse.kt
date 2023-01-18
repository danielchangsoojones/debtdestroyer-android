package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("QuizTopic")
data class QuizTopicParse(
    var prize_amount: Int = 0,
    var name: String = "",
    var ticker: String = "",
    var start_time: StartTime,
    var current_time_seconds: Int = 0
) : SuperParseObject() {

    override fun toString(): String {
        return "QuizTopicParse(prize_amount=$prize_amount, name='$name', ticker='$ticker', start_time=$start_time, current_time_seconds=$current_time_seconds)"
    }
}

data class StartTime(var iso: String = "")