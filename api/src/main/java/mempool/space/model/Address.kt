package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "address") val address: String,
    @Json(name = "chain_stats") val chainStats: Stats,
    @Json(name = "mempool_stats") val mempoolStats: Stats,
)
