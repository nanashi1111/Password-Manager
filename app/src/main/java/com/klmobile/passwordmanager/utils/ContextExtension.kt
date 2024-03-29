package com.klmobile.passwordmanager.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
  Toast.makeText(this, message, duration).show()
}

fun Context.toast(messageId: Int, duration: Int = Toast.LENGTH_SHORT) {
  Toast.makeText(this, messageId, duration).show()
}