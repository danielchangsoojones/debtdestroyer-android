package com.debtdestroyer.android.ui.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment


inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)

}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

fun Context.getSmsIntent() {
    val uri = Uri.parse("smsto:3176905323")
    val i = Intent(Intent.ACTION_SENDTO, uri)
    i.putExtra("sms_body", "Please help me reset my password for debtdestroyer")
    startActivity(i)
}