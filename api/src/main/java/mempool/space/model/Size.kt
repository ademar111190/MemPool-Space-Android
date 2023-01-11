package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Size(
    @Json(name = "avgHeight") val averageHeight: Long,
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "avgSize") val averageSize: Long,
)
