package mempool.space.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import mempool.space.R

sealed class Page(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
) {

    object MainNetPage : Page(
        route = "main_net_page",
        title = R.string.page_title_mainnet,
        icon = R.drawable.ic_mainnet,
    )

    object SignNetPage : Page(
        route = "sign_net_page",
        title = R.string.page_title_signet,
        icon = R.drawable.ic_signet,
    )

    object TestNetPage : Page(
        route = "test_net_page",
        title = R.string.page_title_testnet,
        icon = R.drawable.ic_testnet,
    )

    object ConsolePage : Page(
        route = "console_page",
        title = R.string.page_title_console,
        icon = R.drawable.ic_console,
    )

    object SettingsPage : Page(
        route = "settings_page",
        title = R.string.page_title_settings,
        icon = R.drawable.ic_settings,
    )

}
