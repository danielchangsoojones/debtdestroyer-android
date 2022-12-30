package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("Winner")
data class WinnersParse(
    var winning_date: String = "",
    var amount_won_dollars: Int
) : ParseObject() {

}

data class WinningDate(
    var isp: String = ""
) : ParseObject() {

}