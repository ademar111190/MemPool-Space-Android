package mempool.space.page.console.address

import mempool.space.model.Address

sealed class GetAddressState {

    object Loading : GetAddressState()

    data class FillState(
        val address: String,
        val fetching: Boolean,
    ) : GetAddressState()

    data class SuccessState(
        val result: Address,
    ) : GetAddressState()

    data class ErrorState(
        val error: String,
    ) : GetAddressState()

}

fun GetAddressState.address(): String? = when (this) {
    is GetAddressState.FillState -> address
    is GetAddressState.SuccessState -> result.address
    else -> null
}
