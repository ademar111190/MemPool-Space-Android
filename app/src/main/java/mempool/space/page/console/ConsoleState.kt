package mempool.space.page.console

sealed class ConsoleState {

    object Loading : ConsoleState()

    data class ConsoleData(
        val data: Map<ApiRegion, List<ApiMethod>>
    ) : ConsoleState()

}

data class ApiRegion(
    val name: String,
)

data class ApiMethod(
    val name: String,
)
