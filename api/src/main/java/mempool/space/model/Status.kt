package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Status(
    @Json(name = "confirmed") val confirmed: Boolean,
    @Json(name = "block_height") val blockHeight: Long,
    @Json(name = "block_hash") val blockHash: String,
    @Json(name = "block_time") val blockTime: Long,
)
