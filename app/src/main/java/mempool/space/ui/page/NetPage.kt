package mempool.space.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mempool.space.model.Network

@Composable
fun NetPage(
    navController: NavController,
    modifier: Modifier = Modifier,
    network: Network,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$network",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NetPagePreview() {
    SettingsPage(
        navController = rememberNavController(),
    )
}
