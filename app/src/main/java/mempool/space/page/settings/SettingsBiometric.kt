package mempool.space.page.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import mempool.space.R
import mempool.space.activity.getActivity
import mempool.space.biometric.BiometricState
import mempool.space.biometric.BiometricViewModel

@Composable
fun SettingsBiometric(
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<BiometricViewModel>()
    val state by viewModel.state.collectAsState()
    viewModel.loadBiometricState()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(stringResource(R.string.biometrics_labels))

        if (state is BiometricState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(16.dp),
            )
        }

        val activity = LocalContext.current.getActivity()
        Switch(
            modifier = Modifier.padding(
                start = 8.dp,
            ),
            checked = state is BiometricState.Enrolled,
            enabled = state is BiometricState.Enrolled || state is BiometricState.Available,
            onCheckedChange = {
                if (state is BiometricState.Enrolled) {
                    viewModel.disableBiometric()
                } else {
                    viewModel.enableBiometric(activity)
                }
            }
        )
    }
}
