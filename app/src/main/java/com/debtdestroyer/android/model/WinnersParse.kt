package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ParseFileUtils
import com.parse.ktx.putOrIgnore
import javax.inject.Inject


@ParseClassName("Winner")
class WinnersParse @Inject constructor() : SuperParseObject() {

    var winningDate: String?
        get() = get(KEY_WINNING_DATE) as String
        set(value) = putOrIgnore(KEY_WINNING_DATE, value)

    var amountWonDollars: Int?
        get() = get(KEY_AMOUNT_WON_DOLLARS) as Int?
        set(value) = putOrIgnore(KEY_AMOUNT_WON_DOLLARS, value)
    //var video: ParseFileUtils

    companion object {
        const val KEY_WINNING_DATE = "winning_date"
        const val KEY_AMOUNT_WON_DOLLARS = "amount_won_dollars"
    }
}