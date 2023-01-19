package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ktx.putOrIgnore
import java.util.*
import javax.inject.Inject

@ParseClassName("QuizTopic")
class QuizTopicParse @Inject constructor() : SuperParseObject() {

    var prizeAmount: Int?
        get() = get(KEY_PRICE_AMOUNT) as Int
        set(value) = putOrIgnore(KEY_PRICE_AMOUNT, value)

    var currentTimeSeconds: Int?
        get() = get(KEY_CURRENT_TIME_SECONDS) as Int
        set(value) = putOrIgnore(KEY_CURRENT_TIME_SECONDS, value)

    var startTime: Date?
        get() = get(KEY_START_TIME) as Date
        set(value) = putOrIgnore(KEY_START_TIME, value)

    var name: String?
        get() = get(KEY_NAME).toString()
        set(value) = putOrIgnore(KEY_NAME, value)

    var ticker: String?
        get() = get(KEY_TICKER).toString()
        set(value) = putOrIgnore(KEY_TICKER, value)

    companion object {
        const val KEY_PRICE_AMOUNT = "prize_amount"
        const val KEY_NAME = "name"
        const val KEY_TICKER = "ticker"
        const val KEY_START_TIME = "start_time"
        const val KEY_CURRENT_TIME_SECONDS = "current_time_seconds"
    }
}