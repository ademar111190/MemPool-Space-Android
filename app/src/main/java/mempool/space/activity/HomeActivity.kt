package mempool.space.activity

import android.content.Context
import android.content.Intent
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
import mempool.space.page.console.ConsolePage
import mempool.space.page.settings.SettingsPage
import mempool.space.ui.Page
import mempool.space.ui.page.NetPage
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

                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        selectedIndex = when (destination.route) {
                            Page.MainNetPage.route -> 0
                            Page.SignNetPage.route -> 1
                            Page.TestNetPage.route -> 2
                            Page.ConsolePage.route -> 3
                            Page.SettingsPage.route -> 4
                            else -> 0
                        }
                    }

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
                            intent?.getStringExtra(INITIAL_ROUTE) ?: Page.MainNetPage.route,
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
                                ConsolePage()
                            }
                            composable(Page.SettingsPage.route) {
                                SettingsPage()
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val INITIAL_ROUTE = "mempool.space.action.INITIAL_ROUTE"

        fun intent(context: Context, initialRoute: String? = null): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(INITIAL_ROUTE, initialRoute)
            return intent
        }
    }
}
