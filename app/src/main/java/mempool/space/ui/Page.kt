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
        icon = R.drawable.currency_bitcoin,
    )

    object SignNetPage : Page(
        route = "sign_net_page",
        title = R.string.page_title_signet,
        icon = R.drawable.design_services,
    )

    object TestNetPage : Page(
        route = "test_net_page",
        title = R.string.page_title_testnet,
        icon = R.drawable.bug_report,
    )

    object ConsolePage : Page(
        route = "console_page",
        title = R.string.page_title_console,
        icon = R.drawable.terminal,
    )

    object SettingsPage : Page(
        route = "settings_page",
        title = R.string.page_title_settings,
        icon = R.drawable.settings,
    )

}
