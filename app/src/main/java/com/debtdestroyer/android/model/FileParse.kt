package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ParseFileUtils
import com.parse.ParseObject

@ParseClassName("video")
data class FileParse(
    var winning_date: String = "",
    var amount_won_dollars: String = "", var video: ParseFileUtils
) : SuperParseObject() {

    override fun toString(): String {
        return "WinnersParse(winning_date='$winning_date', amount_won_dollars='$amount_won_dollars', video=)"
    }
}