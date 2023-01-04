package mempool.space.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mempool.space.R
import mempool.space.biometric.BiometricState
import mempool.space.biometric.BiometricViewModel
import mempool.space.ui.Page

@AndroidEntryPoint
class StartActivity : FragmentActivity() {

    private val biometricViewModel: BiometricViewModel by viewModels()

    private val load by lazy { findViewById<View>(R.id.load) }
    private val errorLabel by lazy { findViewById<TextView>(R.id.error_label) }
    private val retry by lazy { findViewById<Button>(R.id.retry_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        biometricViewModel.state.onEach {
            when (val state = it) {
                is BiometricState.Enrolled -> {
                    load.visibility = View.VISIBLE
                    errorLabel.visibility = View.GONE
                    retry.visibility = View.GONE

                    if (state.sessionAuthenticated) {
                        goHome()
                    } else {
                        biometricViewModel.promptBiometricAuthentication(this@StartActivity)
                    }
                }
                is BiometricState.EnrolledButUnavailable -> {
                    load.visibility = View.GONE
                    errorLabel.visibility = View.VISIBLE
                    retry.visibility = View.VISIBLE

                    errorLabel.text = state.reason
                }
                is BiometricState.AuthFailed -> {
                    load.visibility = View.GONE
                    errorLabel.visibility = View.GONE
                    retry.visibility = View.VISIBLE
                }
                else -> {
                    load.visibility = View.VISIBLE
                    errorLabel.visibility = View.GONE
                    retry.visibility = View.GONE

                    goHome()
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.Main))

        checkBiometrics()
        retry.setOnClickListener { checkBiometrics() }
    }

    private fun checkBiometrics() {
        biometricViewModel.loadBiometricState()
    }

    private fun goHome() {
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
