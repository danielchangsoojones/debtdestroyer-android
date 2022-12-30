package com.debtdestroyer.android.model

import com.parse.ParseClassName

@ParseClassName("DebtAccount")
data class DebtAccountsParse(
    var remaining_balance: String = "",
    var title: String = "",
    var logoImg: String = ""
) : SuperParseObject() {


}