package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlockCount(
    @Json(name = "24h") val hour24: Long,
    @Json(name = "1w") val week1: Long,
    @Json(name = "all") val all: Long,
)
