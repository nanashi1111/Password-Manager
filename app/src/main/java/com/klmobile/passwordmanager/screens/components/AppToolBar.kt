/**
 * @file AppToolBar.kt
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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(
  title: String,
  color: Color? = null,
  navigationIcon: ImageVector? = null,
  onNavigationClicked: (() -> Unit)? = null,
  actionIcons: List<ImageVector> = emptyList(),
  onActionsClicked: List<() -> Unit> = emptyList()
) {
  TopAppBar(
    title = { Text(text = title, style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary)) },
    colors = TopAppBarDefaults.smallTopAppBarColors(
      containerColor = if (color == null) {
        MaterialTheme.colorScheme.primary
      } else {
        color
      }
    ),
    navigationIcon = {
      navigationIcon?.let {
        Icon(
          modifier = Modifier
            .clickable { onNavigationClicked?.invoke() }
            .padding(15.dp),
          imageVector = it, contentDescription = "Icon",
          tint = MaterialTheme.colorScheme.onPrimary
        )
      }
    },
    actions = {
      if (actionIcons.size == onActionsClicked.size)
        actionIcons.forEach {
          val index = actionIcons.indexOf(it)
          Icon(
            modifier = Modifier
              .clickable {
                onActionsClicked[index].invoke()
              }
              .padding(15.dp),
            imageVector = it, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary)
        }
    }
  )
}