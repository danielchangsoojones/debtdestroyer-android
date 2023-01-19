package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ktx.putOrIgnore
import javax.inject.Inject


@ParseClassName("QuizScore")
class QuizScoreParse @Inject constructor() : SuperParseObject() {

    var score: Int?
        get() = get(KEY_SCORE) as Int?
        set(value) = putOrIgnore(KEY_SCORE, value)

    companion object {
        const val KEY_SCORE = "score"
    }

}