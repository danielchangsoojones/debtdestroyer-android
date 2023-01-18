package com.debtdestroyer.android.model

import com.parse.ParseClassName

@ParseClassName("Transaction")
data class TransactionParse(
    var ticketCount: Int = 0,
    var totalAmountPaidToLoan: Int = 0,
    var progressMeterTitle: String = ""
) : SuperParseObject() {

    fun getTicket(): String {
        if (ticketCount == 0)
            return "0"
        return ticketCount.toString()
    }


}