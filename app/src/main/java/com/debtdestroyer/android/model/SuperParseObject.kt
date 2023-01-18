package com.debtdestroyer.android.model

import com.parse.ParseObject

open class SuperParseObject(
    var createdAt: String = "",
    var updatedAt: String = ""

) : ParseObject() {

}