package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class Difficulty(
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "difficulty") val difficulty: BigDecimal,
    @Json(name = "height") val height: Long,
)
