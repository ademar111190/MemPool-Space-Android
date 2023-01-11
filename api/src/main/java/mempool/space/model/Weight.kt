package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weight(
    @Json(name = "avgHeight") val averageHeight: Long,
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "avgWeight") val averageWeight: Long,
)
