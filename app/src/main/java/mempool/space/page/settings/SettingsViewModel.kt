package mempool.space.page.settings

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mempool.space.R
import mempool.space.storage.ApiPreferences
import mempool.space.usecase.UrlChecker
import org.slf4j.LoggerFactory
import java.io.File
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val resources: Resources,
    private val storage: ApiPreferences,
    private val urlChecker: UrlChecker,
) : ViewModel() {
    private val log = LoggerFactory.getLogger("SettingsViewModel")

    val state = MutableStateFlow<SettingsState>(SettingsState.Loading)

    init {
        state.onEach {
            log.info("State: $it")
        }.launchIn(viewModelScope)
        loadInitialData()
    }

    fun changeMemPoolUrl(url: String) = viewModelScope.launch {
        log.debug("changeMemPoolUrl: $url")
        state.emit(SettingsState.EditingUrl(url))
    }

    fun saveMemPoolUrl() = viewModelScope.launch {
        log.debug("saveMemPoolUrl")
        val url = state.value.url()
        state.emit(SettingsState.CheckingUrl(url))
        val isValid = urlChecker.isUrlValid(url)
        if (isValid) {
            storage.updateMemPoolUrl(url)
            state.emit(SettingsState.UrlValid(url))
        } else {
            state.emit(
                SettingsState.UrlInvalid(
                    url,
                    error = resources.getString(R.string.setting_mempool_url_invalid),
                )
            )
        }
    }

    fun resetMemPoolUrl() = viewModelScope.launch {
        log.debug("resetMemPoolUrl")
        storage.resetMemPoolUrl()
        loadInitialData()
    }

    @SuppressLint("SdCardPath") // File is specified by logback.xml
    fun shareLogs(context: Activity) = viewModelScope.launch {
        log.debug("shareLogs")
        val logFilePath = "/data/data/mempool.space/files/log.txt"

        val fileUri: Uri
        try {
            fileUri = FileProvider.getUriForFile(context, "mempool.space.fileprovider", File(logFilePath))
        } catch (e: IllegalArgumentException) {
            log.error("The selected file can't be shared:", e)
            return@launch
        }

        val shareTitle = resources.getString(R.string.setting_actions_share_logs)
        context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_STREAM, fileUri)
            putExtra(Intent.EXTRA_SUBJECT, shareTitle)
            putExtra(Intent.EXTRA_TEXT, shareTitle)
        }, shareTitle))
    }

    private fun loadInitialData() = viewModelScope.launch {
        log.debug("loadInitialData")
        val memPoolUrl = storage.readMemPoolUrl()
        state.emit(SettingsState.UrlValid(memPoolUrl))
    }

}
