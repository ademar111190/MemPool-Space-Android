package mempool.space.page.settings

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mempool.space.R
import mempool.space.storage.ApiPreferences
import mempool.space.usecase.UrlChecker
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val resources: Resources,
    private val storage: ApiPreferences,
    private val urlChecker: UrlChecker,
) : ViewModel() {

    val state = MutableStateFlow<SettingsState>(SettingsState.Loading)

    init {
        loadInitialData()
    }

    fun changeMemPoolUrl(url: String) = viewModelScope.launch {
        state.value = SettingsState.EditingUrl(url)
    }

    fun saveMemPoolUrl() = viewModelScope.launch {
        val url = state.value.url()
        state.value = SettingsState.CheckingUrl(url)
        val isValid = urlChecker.isUrlValid(url)
        if (isValid) {
            storage.updateMemPoolUrl(url)
            state.value = SettingsState.UrlValid(url)
        } else {
            state.value = SettingsState.UrlInvalid(
                url,
                error = resources.getString(R.string.setting_mempool_url_invalid),
            )
        }
    }

    fun resetMemPoolUrl() = viewModelScope.launch {
        storage.resetMemPoolUrl()
        loadInitialData()
    }

    private fun loadInitialData() = viewModelScope.launch {
        val memPoolUrl = storage.readMemPoolUrl()
        state.value = SettingsState.UrlValid(memPoolUrl)
    }

}
