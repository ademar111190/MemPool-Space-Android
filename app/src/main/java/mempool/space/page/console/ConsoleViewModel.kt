package mempool.space.page.console

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mempool.space.R
import org.slf4j.LoggerFactory
import javax.inject.Inject

@HiltViewModel
class ConsoleViewModel @Inject constructor(
    private val resources: Resources,
) : ViewModel() {
    private val log = LoggerFactory.getLogger("ConsoleViewModel")

    val state = MutableStateFlow<ConsoleState>(ConsoleState.Loading)

    init {
        state.onEach {
            log.info("State: $it")
        }.launchIn(viewModelScope)
        loadInitialData()
    }

    private fun loadInitialData() = viewModelScope.launch {
        log.debug("loadInitialData")
        state.emit(
            ConsoleState.ConsoleData(
                mapOf(
                    ApiRegion(resources.getString(R.string.console_mempool_api_region_address)) to listOf(),
                    ApiRegion(resources.getString(R.string.console_mempool_api_region_block)) to listOf(),
                    ApiRegion(resources.getString(R.string.console_mempool_api_region_mining)) to listOf(),
                    ApiRegion(resources.getString(R.string.console_mempool_api_region_fees)) to listOf(),
                    ApiRegion(resources.getString(R.string.console_mempool_api_region_mempool)) to listOf(),
                )
            )
        )
    }

}
