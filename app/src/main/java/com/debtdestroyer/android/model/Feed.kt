package com.debtdestroyer.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feed(
    val title: String,
    val resId: Int,
    val url: String
) : Parcelable {

}
