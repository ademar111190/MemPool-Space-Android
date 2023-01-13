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
import androidx.navigation.NavController
import mempool.space.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsolePage(
    navController: NavController,
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
            when (val stateRef = state) {
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
                    stateRef.data.forEach { (region, methods) ->
                        ConsoleItemHead(region.name)
                        methods.forEach { method ->
                            ConsoleItem(navController, method.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConsoleItemHead(name: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
                bottom = 8.dp,
            ),
        text = name,
    )
}

@Composable
fun ConsoleItem(navController: NavController, name: String) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
                bottom = 8.dp,
            ),
        onClick = {
            navController.navigate("console/$name")
        }) {
        Text(text = name)
    }
}
