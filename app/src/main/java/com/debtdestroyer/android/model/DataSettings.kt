package com.debtdestroyer.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataSettings(
    val title: String,
    val resId: Int,
    val type: Setting
) : Parcelable {

}

enum class Setting {
    WINNER_INFORMATION,
    CONNECTED_ACCOUNTS,
    CONTACT_US,
    LEGAL_DISCLOSURE,
    LEAVE_FEEDBACK,
    LOGOUT,
    DELETE_ACCOUNT
}
