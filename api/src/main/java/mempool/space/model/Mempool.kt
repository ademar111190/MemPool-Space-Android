package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Mempool(
    @Json(name = "count") val count: Long,
    @Json(name = "vsize") val vSize: Long,
    @Json(name = "total_fee") val totalFee: Long,
    @Json(name = "fee_histogram") val feeHistogram: List<Histogram>,
)
