package mempool.space.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mempool.space.model.Network
import mempool.space.ui.Page
import mempool.space.ui.page.ConsolePage
import mempool.space.ui.page.NetPage
import mempool.space.ui.page.SettingsPage
import mempool.space.ui.theme.MemPoolSpaceTheme

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemPoolSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    var selectedIndex by remember { mutableStateOf(0) }

                    Scaffold(bottomBar = {
                        NavigationBar {
                            listOf(
                                Page.MainNetPage,
                                Page.SignNetPage,
                                Page.TestNetPage,
                                Page.ConsolePage,
                                Page.SettingsPage,
                            ).forEachIndexed { index, item ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painterResource(id = item.icon),
                                            contentDescription = getString(item.title),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    },
                                    label = {
                                        Text(getString(item.title))
                                    },
                                    selected = selectedIndex == index,
                                    onClick = {
                                        if (selectedIndex != index) {
                                            selectedIndex = index
                                            navController.navigate(item.route)
                                        }
                                    }
                                )
                            }
                        }
                    }) { padding ->
                        NavHost(
                            navController,
                            Page.MainNetPage.route,
                            Modifier.padding(padding),
                        ) {
                            composable(Page.MainNetPage.route) {
                                NetPage(
                                    navController,
                                    network = Network.Mainnet,
                                )
                            }
                            composable(Page.SignNetPage.route) {
                                NetPage(
                                    navController,
                                    network = Network.Signet,
                                )
                            }
                            composable(Page.TestNetPage.route) {
                                NetPage(
                                    navController,
                                    network = Network.Testnet,
                                )
                            }
                            composable(Page.ConsolePage.route) {
                                SettingsPage(navController)
                            }
                            composable(Page.SettingsPage.route) {
                                ConsolePage(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
