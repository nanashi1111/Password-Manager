package com.klmobile.passwordmanager.utils

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

fun getColor(colorString: String): Color {
  return Color(colorString.toColorInt())
}

fun String.toColor() : Color {
  return Color(toColorInt())
}

fun Color.toHexString(): String {
  val alpha = this.alpha*255
  val red = this.red * 255
  val green = this.green * 255
  val blue = this.blue * 255
  return String.format("#%02x%02x%02x%02x", alpha.toInt(),red.toInt(), green.toInt(), blue.toInt())
}