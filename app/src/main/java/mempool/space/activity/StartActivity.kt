package mempool.space.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import mempool.space.ui.Page

@AndroidEntryPoint
class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val action = intent?.action?.let { initialAction ->
            when (initialAction) {
                "mempool.space.action.mainnet" -> Page.MainNetPage.route
                "mempool.space.action.signet" -> Page.SignNetPage.route
                "mempool.space.action.testnet" -> Page.TestNetPage.route
                "mempool.space.action.console" -> Page.ConsolePage.route
                "mempool.space.action.settings" -> Page.SettingsPage.route
                else -> null
            }
        }

        startActivity(HomeActivity.intent(this, action))
        finish()
    }
}
