package mempool.space.biometric

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Build
import android.provider.Settings
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import javax.inject.Inject

@HiltViewModel
class BiometricViewModel @Inject constructor(
    private val resources: Resources,
    private val prefs: SharedPreferences,
    private val biometricManager: BiometricManager,
) : ViewModel() {
    private val log = LoggerFactory.getLogger("BiometricViewModel")

    val state = MutableStateFlow<BiometricState>(BiometricState.Loading)

    init {
        state.onEach {
            log.info("State: $it")
        }.launchIn(viewModelScope)
    }

    fun loadBiometricState() = viewModelScope.launch {
        log.debug("loadBiometricState")
        val enabled = prefs.getBoolean(KEY_BIOMETRICS_ENABLED, false)
        state.emit(
            when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
                BiometricManager.BIOMETRIC_SUCCESS -> if (enabled) {
                    BiometricState.Enrolled(sessionAuthenticated = false)
                } else {
                    BiometricState.Available
                }
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> if (enabled) {
                    BiometricState.EnrolledButUnavailable(
                        reason = resources.getString(R.string.biometrics_no_hardware),
                    )
                } else {
                    BiometricState.Unavailable(
                        reason = resources.getString(R.string.biometrics_no_hardware),
                    )
                }
                else -> if (enabled) {
                    BiometricState.EnrolledButUnavailable(
                        reason = resources.getString(R.string.biometrics_unavailable),
                    )
                } else {
                    BiometricState.Unavailable(
                        reason = resources.getString(R.string.biometrics_unavailable),
                    )
                }
            }
        )
    }

    fun promptBiometricAuthentication(context: FragmentActivity) = viewModelScope.launch {
        log.debug("promptBiometricAuthentication context=$context")
        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(context, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                log.debug("onAuthenticationSucceeded result=$result")
                viewModelScope.launch {
                    state.emit(BiometricState.Enrolled(sessionAuthenticated = true))
                }
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                log.debug("onAuthenticationError errorCode=$errorCode errString=$errString")
                viewModelScope.launch {
                    state.emit(BiometricState.AuthFailed)
                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                log.debug("onAuthenticationFailed")
                viewModelScope.launch {
                    state.emit(BiometricState.AuthFailed)
                }
            }
        })
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(resources.getString(R.string.biometrics_prompt_title))
            .setSubtitle(resources.getString(R.string.biometrics_prompt_subtitle))
            .setNegativeButtonText(resources.getString(R.string.biometrics_prompt_negative))
            .build()
        biometricPrompt.authenticate(promptInfo)
    }

    fun disableBiometric() = viewModelScope.launch {
        log.debug("disableBiometric")
        prefs.edit().putBoolean(KEY_BIOMETRICS_ENABLED, false).apply()
        state.emit(BiometricState.Available)
    }

    fun enableBiometric(context: Activity) = viewModelScope.launch {
        log.debug("enableBiometric context=$context")
        prefs.edit().putBoolean(KEY_BIOMETRICS_ENABLED, true).apply()
        state.emit(BiometricState.Enrolled(sessionAuthenticated = false))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL)
            enrollIntent.putExtra(
                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BIOMETRIC_STRONG or DEVICE_CREDENTIAL
            )
            context.startActivityForResult(enrollIntent, REQUEST_CODE_BIOMETRIC_ENROLL)
        }
    }

}

private const val KEY_BIOMETRICS_ENABLED = "biometrics_enabled"
const val REQUEST_CODE_BIOMETRIC_ENROLL = 1
