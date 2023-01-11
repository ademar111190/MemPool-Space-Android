package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlockFee(
    @Json(name = "avgHeight") val averageHeight: Long,
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "avgFees") val averageFees: Long,
    @Json(name = "USD") val usd: Long,
)
