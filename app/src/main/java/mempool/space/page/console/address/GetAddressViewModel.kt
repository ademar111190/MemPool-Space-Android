package mempool.space.page.console.address

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mempool.space.R
import mempool.space.api.MemPoolSpaceApi
import org.slf4j.LoggerFactory
import javax.inject.Inject

@HiltViewModel
class GetAddressViewModel @Inject constructor(
    private val api: MemPoolSpaceApi,
    private val resources: Resources,
    private val prefs: SharedPreferences,
) : ViewModel() {
    private val log = LoggerFactory.getLogger("AddressViewModel")

    val state = MutableStateFlow<GetAddressState>(GetAddressState.Loading)

    init {
        state.onEach {
            log.info("State: $it")
        }.launchIn(viewModelScope)
        loadInitialData()
    }

    fun loadInitialData() = viewModelScope.launch {
        log.debug("loadInitialData")
        state.emit(
            GetAddressState.FillState(
                address = prefs.getString(LAST_ADDRESS_INPUT, DEFAULT_ADDRESS) ?: DEFAULT_ADDRESS,
                fetching = false,
            )
        )
    }

    fun changeAddress(address: String) = viewModelScope.launch {
        log.debug("changeAddress: $address")
        prefs.edit().putString(LAST_ADDRESS_INPUT, address).apply()
        state.emit(GetAddressState.FillState(address = address, fetching = false))
    }

    fun execute() = viewModelScope.launch {
        log.debug("execute")
        val currentState = state.value
        val address = currentState.address()
        if (address == null) {
            log.warn("No address to execute")
            return@launch
        }
        state.emit(GetAddressState.FillState(address = address, fetching = true))

        val response = api.getAddress(address)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                state.emit(GetAddressState.SuccessState(result = body))
            } else {
                state.emit(
                    GetAddressState.ErrorState(
                        error = resources.getString(R.string.app_unknown_error),
                    )
                )
            }
        } else {
            state.emit(
                GetAddressState.ErrorState(
                    error = response.errorBody()?.string() ?: resources.getString(R.string.app_unknown_error),
                )
            )
        }
    }

}

private const val LAST_ADDRESS_INPUT = "last_address_input"
private const val DEFAULT_ADDRESS = "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"
