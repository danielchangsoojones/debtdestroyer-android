package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ktx.putOrIgnore
import javax.inject.Inject


@ParseClassName("QuizScore")
class QuizScoreParse @Inject constructor() : SuperParseObject() {

    var deadlineMessage: String?
        get() = get(KEY_DEADLINE_MSG) as String?
        set(value) = putOrIgnore(KEY_DEADLINE_MSG, value)

    companion object {
        const val KEY_DEADLINE_MSG = "deadlineMessage"
    }

}