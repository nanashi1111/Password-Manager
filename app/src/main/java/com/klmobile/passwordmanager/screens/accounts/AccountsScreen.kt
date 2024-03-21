package com.klmobile.passwordmanager.screens.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klmobile.passwordmanager.R
import com.klmobile.passwordmanager.screens.accounts.components.AccountItem
import com.klmobile.passwordmanager.screens.accounts.components.CreateAccount
import com.klmobile.passwordmanager.screens.components.AppLoadingIndicator
import com.klmobile.passwordmanager.screens.components.AppToolBar
import com.klmobile.passwordmanager.ui.theme.PasswordManagerTheme
import com.passwordmanager.domain.State
import com.passwordmanager.domain.entities.Account
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen(
    onCreateAccountClicked: (() -> Unit)? = null,
    onAccountSelected: ((Account) -> Unit)? = null,
    accountsState: State<List<Account>>
) {
    Scaffold(topBar = {
        AppToolBar(title = stringResource(id = R.string.app_name))
    }, floatingActionButton = {
        CreateAccount(onClicked = onCreateAccountClicked)
    }, floatingActionButtonPosition = FabPosition.End) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (accountsState) {
                is State.DataState -> LazyColumn(content = {
                    items(accountsState.data.size, key = { accountsState.data[it].date }) {
                        AccountItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                                .clickable { onAccountSelected?.invoke(accountsState.data[it]) },
                            account = accountsState.data[it],
                        )
                    }
                })

                is State.ErrorState -> Text(text = accountsState.exception.message ?: "Error!")
                else -> {
                    AppLoadingIndicator(modifier = Modifier.fillMaxSize())
                }
            }

        }
    }
}

@Preview
@Composable
fun AccountScreenPreview() {
    PasswordManagerTheme {
        AccountsScreen(
            accountsState = State.DataState(
                mutableListOf(
                    Account(
                        Date().time,
                        "Facebook",
                        "dtv1111",
                        "dtv1111",
                        "30912jd",
                        "https://facebook.com", ""
                    )
                )
            )
        )
    }
}