package com.klmobile.passwordmanager.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacing(spacing: Dp) {
  Box (modifier = Modifier.height(spacing))
}

@Composable
fun HorizontalSpacing(spacing: Dp) {
  Box (modifier = Modifier.width(spacing))
}
