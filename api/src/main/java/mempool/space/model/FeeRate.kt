package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeeRate(
    @Json(name = "avgHeight") val averageHeight: Long,
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "avgFee_0") val averageFee0: Long,
    @Json(name = "avgFee_10") val averageFee10: Long,
    @Json(name = "avgFee_25") val averageFee25: Long,
    @Json(name = "avgFee_50") val averageFee50: Long,
    @Json(name = "avgFee_75") val averageFee75: Long,
    @Json(name = "avgFee_90") val averageFee90: Long,
    @Json(name = "avgFee_100") val averageFee100: Long,
)
