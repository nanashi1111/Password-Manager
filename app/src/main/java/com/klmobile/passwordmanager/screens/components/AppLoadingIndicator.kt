/**
 * @file AppLoadingIndicator.kt
 * @author Duong Tuan Vu
 *
 * © 2024 Hyundai Motor Company. All Rights Reserved.
 *
 * This software is copyright protected and proprietary to Hyundai Motor Company.
 * Do not copy without prior permission. Any copy of this software or of any
 * derivative work must include the above copyright notice, this paragraph and
 * the one after it.
 *
 * This software is made available on an "AS IS" condition, and Hyundai Motor Company
 * disclaims all warranties of any kind, whether express or implied, statutory or
 * otherwise, including without limitation any warranties of merchantability or
 * fitness for a particular purpose, absence of errors, accuracy, completeness of
 * results or the validity, scope, or non-infringement of any intellectual property.
 */
package com.klmobile.passwordmanager.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppLoadingIndicator(modifier: Modifier) {
    return Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(70.dp))
    }
}