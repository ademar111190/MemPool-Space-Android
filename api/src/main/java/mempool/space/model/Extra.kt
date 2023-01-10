package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Extra(
    @Json(name = "coinbaseRaw") val coinbaseRaw: String,
    @Json(name = "medianFee") val medianFee: Long,
    @Json(name = "feeRange") val feeRange: List<Long>,
    @Json(name = "reward") val reward: Long,
    @Json(name = "totalFees") val totalFees: Long,
    @Json(name = "avgFee") val averageFee: Long,
    @Json(name = "avgFeeRate") val averageFeeRate: Long,
    @Json(name = "pool") val pool: Pool,
)
