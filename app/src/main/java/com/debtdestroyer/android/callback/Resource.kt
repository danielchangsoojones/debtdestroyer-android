package com.debtdestroyer.android.callback

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String = ""
) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, "Success")
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null,"InProgress...")
        }

    }
}