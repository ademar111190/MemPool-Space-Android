package mempool.space.ui.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import mempool.space.R
import mempool.space.storage.SimpleStorage
import mempool.space.usecase.UrlChecker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(
    storage: SimpleStorage,
    urlChecker: UrlChecker,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    var memPoolUrl by rememberSaveable { mutableStateOf(storage.memPoolUrl) }
    var memPoolUrlButtonEnabled by rememberSaveable { mutableStateOf(true) }

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
            verticalArrangement = Arrangement.Top,
        ) {
            TextField(
                value = memPoolUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        top = 0.dp,
                        end = 16.dp,
                        bottom = 8.dp,
                    ),
                onValueChange = {
                    memPoolUrl = it
                    coroutineScope.coroutineContext.cancelChildren()
                    coroutineScope.launch {
                        memPoolUrlButtonEnabled = urlChecker.isUrlValid(memPoolUrl)
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
                        storage.resetMemPoolUrl()
                        memPoolUrl = storage.memPoolUrl
                        memPoolUrlButtonEnabled = true
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
                        storage.memPoolUrl = memPoolUrl
                    },
                    modifier = Modifier.padding(
                        start = 4.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 8.dp,
                    ),
                    enabled = memPoolUrlButtonEnabled,
                ) {
                    Text(stringResource(R.string.setting_mempool_url_save))
                }
            }
        }
    }
}
