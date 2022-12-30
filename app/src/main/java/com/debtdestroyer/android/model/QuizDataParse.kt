package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("Winner")
data class QuizDataParse(
    var winning_date: String = "",
    var amount_won_dollars: Int
) : ParseObject()