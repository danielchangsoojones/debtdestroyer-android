package com.debtdestroyer.android.model

import com.parse.ParseClassName

@ParseClassName("Sweep")
data class SweepParse(
    var prizeAmountTxt: String = "",
    var deadlineTxt: String = "",
    var title: String = ""
) : SuperParseObject() {

    override fun toString(): String {
        return "SweepParse(prizeAmountTxt='$prizeAmountTxt', deadlineTxt='$deadlineTxt', title=$title)"
    }
}