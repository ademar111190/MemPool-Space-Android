package mempool.space.page.console

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import mempool.space.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsolePage(
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<ConsoleViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.page_title_console))
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = if (state == ConsoleState.Loading) Alignment.CenterHorizontally else Alignment.Start,
            verticalArrangement = if (state == ConsoleState.Loading) Arrangement.Center else Arrangement.Top,
        ) {
            when (state) {
                is ConsoleState.Loading -> {
                    CircularProgressIndicator()
                }
                is ConsoleState.ConsoleData -> {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                top = 0.dp,
                                end = 16.dp,
                                bottom = 8.dp,
                            ),
                        text = stringResource(R.string.console_description),
                    )
                }
            }
        }
    }
}
