package com.klmobile.passwordmanager.screens.accounts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klmobile.passwordmanager.screens.components.HorizontalSpacing
import com.klmobile.passwordmanager.ui.theme.PasswordManagerTheme
import com.passwordmanager.domain.entities.Account
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun AccountItem(
  modifier: Modifier = Modifier,
  account: Account,
  onAccountSelected: ((Account) -> Unit)? = null,
) {

  Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
    Box(
      modifier = Modifier
        .size(55.dp)
        .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
      contentAlignment = Alignment.Center
    ) {
      val shortTitle = account.title.split(" ")
        .filter { it.isNotEmpty() }
        .map { "${it[0]}".uppercase() }
        .joinToString("")
      Text(
        text = shortTitle, style = MaterialTheme.typography.titleMedium
          .copy(color = MaterialTheme.colorScheme.onPrimary)
      )
    }
    HorizontalSpacing(spacing = 15.dp)
    Text(
      modifier = Modifier.weight(1f),
      text = account.username, style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground)
    )
    HorizontalSpacing(spacing = 15.dp)
    Text(
      text = SimpleDateFormat("dd/MM/yyyy").format(Date(account.date)),
      style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface)
    )

  }
}

@Preview
@Composable
fun AccountItemPreview() {
  PasswordManagerTheme {
    AccountItem(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 15.dp),
      account = Account(Date().time, "Facebook ring", "Facebook", "", "", "")
    )
  }
}