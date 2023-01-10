package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlockShare(
    @Json(name = "24h") val hour24: Double,
    @Json(name = "1w") val week1: Double,
    @Json(name = "all") val all: Double,
)
