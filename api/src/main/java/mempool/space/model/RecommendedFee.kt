package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecommendedFee(
    @Json(name = "fastestFee") val fastestFee: Long,
    @Json(name = "halfHourFee") val halfHourFee: Long,
    @Json(name = "hourFee") val hourFee: Long,
    @Json(name = "economyFee") val economyFee: Long,
    @Json(name = "minimumFee") val minimumFee: Long,
)
