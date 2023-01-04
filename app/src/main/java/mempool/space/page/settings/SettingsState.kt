package mempool.space.page.settings

sealed class SettingsState {

    object Loading : SettingsState()

    data class EditingUrl(
        val url: String,

    ) : SettingsState()

    data class CheckingUrl(
        val url: String,
    ) : SettingsState()

    data class UrlValid(
        val url: String,
    ) : SettingsState()

    data class UrlInvalid(
        val url: String,
        val error: String,
    ) : SettingsState()

}

fun SettingsState.url(): String = when (this) {
    is SettingsState.UrlValid -> url
    is SettingsState.UrlInvalid -> url
    is SettingsState.EditingUrl -> url
    is SettingsState.CheckingUrl -> url
    is SettingsState.Loading -> ""
}

fun SettingsState.error(): String? = when (this) {
    is SettingsState.UrlInvalid -> error
    else -> null
}
