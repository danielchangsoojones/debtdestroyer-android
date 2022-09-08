package com.debtdestroyer.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Accounts(
    val title: String,
    val resId: Int,
    val balance: String
) : Parcelable {

}
