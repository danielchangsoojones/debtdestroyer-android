package com.debtdestroyer.android.utils

class AppUtils {
}

const val DAILY_TRIVIA_URL = "https://cdn.dribbble.com/users/720738/screenshots/14260851/media/66178d7c84c854ecf186d86bc7f9de5f.mp4"

const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
fun isEmailValid(email: String): Boolean {
    return EMAIL_REGEX.toRegex().matches(email);
}