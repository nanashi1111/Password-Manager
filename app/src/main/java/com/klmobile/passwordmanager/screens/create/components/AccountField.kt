package com.klmobile.passwordmanager.screens.create.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.klmobile.passwordmanager.R
import com.klmobile.passwordmanager.ui.theme.VerticalSpacing
import com.passwordmanager.domain.entities.AccountField
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountField(
    modifier: Modifier = Modifier,
    initialText: String = "",
    accountField: AccountField = AccountField.TITLE,
    onChanged: ((String, AccountField) -> Unit)? = null,
) {
    var text by remember {
        mutableStateOf(initialText)
    }
    LaunchedEffect(key1 = initialText, block = {
        Timber.d("AccountTitle: type = ${accountField} ; initText = $initialText")
    })
    Column(modifier = modifier) {
        VerticalSpacing(spacing = 15.dp)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text, onValueChange = {
                text = it
                onChanged?.invoke(it, accountField)
            },
            placeholder = {
                Text(
                    text = when (accountField) {
                        AccountField.NOTE -> stringResource(id = R.string.note)
                        AccountField.PASSWORD -> stringResource(id = R.string.password)
                        AccountField.TITLE -> stringResource(id = R.string.title)
                        AccountField.WEBSITE -> stringResource(id = R.string.website)
                        AccountField.USERNAME -> stringResource(id = R.string.username)
                    }
                )
            })

    }

}