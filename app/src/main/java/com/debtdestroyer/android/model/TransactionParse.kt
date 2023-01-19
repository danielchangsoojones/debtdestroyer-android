package com.debtdestroyer.android.model

import com.parse.ParseClassName
import com.parse.ktx.putOrIgnore
import javax.inject.Inject

@ParseClassName("Transaction")
class TransactionParse @Inject constructor()  : SuperParseObject() {

    var ticketCount: Int?
        get() = get(KEY_TICKET_COUNT) as Int
        set(value) = putOrIgnore(KEY_TICKET_COUNT, value)

    var totalAmountPaidToLoan: Int?
        get() = get(KEY_TOTAL_AMOUNT_PAID_TO_LOAN) as Int
        set(value) = putOrIgnore(KEY_TOTAL_AMOUNT_PAID_TO_LOAN, value)

    var progressMeterTitle: String?
        get() = get(KEY_PROGRESS_METER_TITLE).toString()
        set(value) = putOrIgnore(KEY_PROGRESS_METER_TITLE, value)


    companion object {
        const val KEY_TICKET_COUNT = "ticketCount"
        const val KEY_TOTAL_AMOUNT_PAID_TO_LOAN = "totalAmountPaidToLoan"
        const val KEY_PROGRESS_METER_TITLE = "progressMeterTitle"
    }

    override fun toString(): String {
        return "TransactionParse(ticketCount=$ticketCount, totalAmountPaidToLoan=$totalAmountPaidToLoan, progressMeterTitle=$progressMeterTitle)"
    }


}