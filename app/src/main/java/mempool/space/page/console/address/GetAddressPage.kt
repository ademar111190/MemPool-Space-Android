package mempool.space.page.console.address

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import mempool.space.page.console.address.GetAddressState.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAddressPage(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<GetAddressViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.nav_back),
                        )
                    }
                },
                title = {
                    Text("getAddress")
                },
            )
        },
    ) { innerPadding ->
        val centerContent = state == Loading || state is ErrorState
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = if (centerContent) Alignment.CenterHorizontally else Alignment.Start,
            verticalArrangement = if (centerContent) Arrangement.Center else Arrangement.Top,
        ) {
            when (val stateRef = state) {
                is Loading -> {
                    CircularProgressIndicator()
                }
                is ErrorState -> {
                    Text(stateRef.error)
                    Button(
                        onClick = {
                            viewModel.loadInitialData()
                        },
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                            bottom = 8.dp,
                        ),
                    ) {
                        Text(stringResource(id = R.string.start_over))
                    }
                }
                else -> {
                    TextField(
                        value = stateRef.address() ?: "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                top = 0.dp,
                                end = 16.dp,
                                bottom = 8.dp,
                            ),
                        onValueChange = {
                            viewModel.changeAddress(it)
                        },
                        label = {
                            Text(stringResource(R.string.bitcoin_address))
                        },
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Button(
                            onClick = {
                                viewModel.execute()
                            },
                            modifier = Modifier.padding(
                                start = 16.dp,
                                top = 8.dp,
                                end = 16.dp,
                                bottom = 8.dp,
                            ),
                            enabled = stateRef !is FillState || !stateRef.fetching,
                        ) {
                            Text(stringResource(R.string.execute))
                            if (stateRef is FillState && stateRef.fetching) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .size(16.dp),
                                )
                            }
                        }
                    }

                    if (stateRef is SuccessState) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 16.dp,
                                    top = 0.dp,
                                    end = 16.dp,
                                    bottom = 8.dp,
                                ),
                            text = stringResource(R.string.result),
                        )
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 16.dp,
                                    top = 0.dp,
                                    end = 16.dp,
                                    bottom = 8.dp,
                                ),
                            text = stateRef.result.toString(),
                        )
                    }
                }
            }
        }
    }
}
