package com.debtdestroyer.android.utils

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import com.debtdestroyer.android.R
import com.google.android.material.textview.MaterialTextView

fun Toast.showCustomToast(title: String, message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate(
        R.layout.custom_toast_layout,
        activity.findViewById(R.id.toast_container)
    )
    // set the text of the TextView of the message
    val textViewTitle = layout.findViewById<MaterialTextView>(R.id.toast_text_title)
    val textViewBody = layout.findViewById<MaterialTextView>(R.id.toast_text_body)
    textViewTitle.text = title
    textViewBody.text = message

    // use the application extension function
    this.apply {
        setGravity(Gravity.TOP, 0, 0)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}