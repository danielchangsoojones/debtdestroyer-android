package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ktx.putOrIgnore
import javax.inject.Inject

@ParseClassName("Sweep")
class SweepParse @Inject constructor() : SuperParseObject() {

    var prizeAmountTxt: String?
        get() = get(KEY_PRIZE_AMOUNT_TXT).toString()
        set(value) = putOrIgnore(KEY_PRIZE_AMOUNT_TXT, value)

    var title: String?
        get() = get(KEY_TITLE).toString()
        set(value) = putOrIgnore(KEY_TITLE, value)

    var deadlineTxt: String?
        get() = get(KEY_DEADLINE_TXT).toString()
        set(value) = putOrIgnore(KEY_DEADLINE_TXT, value)


    companion object {
        const val KEY_TITLE = "title"
        const val KEY_PRIZE_AMOUNT_TXT = "prizeAmountTxt"
        const val KEY_DEADLINE_TXT = "deadlineTxt"
    }

    override fun toString(): String {
        return "SweepParse(prizeAmountTxt=$prizeAmountTxt, title=$title, deadlineTxt=$deadlineTxt)"
    }

}