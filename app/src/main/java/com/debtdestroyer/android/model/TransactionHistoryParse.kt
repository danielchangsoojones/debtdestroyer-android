package com.debtdestroyer.android.model

import com.parse.ParseClassName

@ParseClassName("DebtAccount")
data class TransactionHistoryParse(
    var remaining_balance: String = "",
    var title: String = ""
) : SuperParseObject() {


}