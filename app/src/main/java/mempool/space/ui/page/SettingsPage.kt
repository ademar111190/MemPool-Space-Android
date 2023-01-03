package mempool.space.ui.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mempool.space.R
import mempool.space.storage.SimpleStorage
import mempool.space.usecase.UrlChecker

@OptIn(ExperimentalMaterial3Api::class) @Composable fun SettingsPage(
    navController: NavController,
    storage: SimpleStorage,
    urlChecker: UrlChecker,
    modifier: Modifier = Modifier,
) {
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
            var memPoolUrl by rememberSaveable { mutableStateOf(storage.memPoolUrl) }
            TextField(value = memPoolUrl, modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 0.dp,
                    end = 16.dp,
                    bottom = 8.dp,
                ), onValueChange = {
                memPoolUrl = it
            }, label = {
                Text(stringResource(R.string.setting_mempool_url))
            })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = {
                        storage.resetMemPoolUrl()
                        memPoolUrl = storage.memPoolUrl
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
                    enabled = urlChecker.isUrlValid(memPoolUrl),
                ) {
                    Text(stringResource(R.string.setting_mempool_url_save))
                }
            }
        }
    }
}
