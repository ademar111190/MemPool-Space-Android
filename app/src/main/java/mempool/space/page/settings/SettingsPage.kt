package mempool.space.page.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import mempool.space.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.page_title_settings))
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (state == SettingsState.Loading) Arrangement.Center else Arrangement.Top,
        ) {
            when (state) {
                is SettingsState.Loading -> {
                    CircularProgressIndicator()
                }
                else -> {
                    TextField(
                        value = state.url(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                top = 0.dp,
                                end = 16.dp,
                                bottom = 8.dp,
                            ),
                        onValueChange = {
                            viewModel.changeMemPoolUrl(it)
                        },
                        isError = state is SettingsState.UrlInvalid,
                        supportingText = {
                            state.error()?.let { error ->
                                Text(error)
                            }
                        },
                        label = {
                            Text(stringResource(R.string.setting_mempool_url))
                        },
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Button(
                            onClick = {
                                viewModel.resetMemPoolUrl()
                            },
                            modifier = Modifier.padding(
                                start = 16.dp,
                                top = 8.dp,
                                end = 4.dp,
                                bottom = 8.dp,
                            ),
                        ) {
                            Text(stringResource(R.string.setting_mempool_url_reset))
                        }
                        Button(
                            onClick = {
                                viewModel.saveMemPoolUrl()
                            },
                            modifier = Modifier.padding(
                                start = 4.dp,
                                top = 8.dp,
                                end = 16.dp,
                                bottom = 8.dp,
                            ),
                            enabled = state !is SettingsState.CheckingUrl,
                        ) {
                            Text(stringResource(R.string.setting_mempool_url_save))
                            if (state is SettingsState.CheckingUrl) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .size(16.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
