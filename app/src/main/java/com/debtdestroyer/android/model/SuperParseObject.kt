package com.debtdestroyer.android.model

import com.parse.ParseObject
import com.parse.ktx.putOrIgnore

open class SuperParseObject(
) : ParseObject() {

    var createdAt: String?
        get() = get(KEY_CREATED_AT).toString()
        set(value) = putOrIgnore(KEY_CREATED_AT, createdAt)

    var updatedAt: String?
        get() = get(KEY_UPDATED_AT).toString()
        set(value) = putOrIgnore(KEY_UPDATED_AT, updatedAt)

    companion object {
        const val KEY_CREATED_AT = "createdAt"
        const val KEY_UPDATED_AT = "updatedAt"

        //Common in all objects
        const val KEY_ORDER = "order"
    }
}