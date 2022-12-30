package com.debtdestroyer.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScholarshipModel(
    var scholarshipName: String,
    var deadline: String,
    val amount: String,
    val url: String
) : Parcelable {


}