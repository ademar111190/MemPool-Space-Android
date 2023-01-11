package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MempoolBlock(
    @Json(name = "blockSize") val blockSize: Long,
    @Json(name = "blockVSize") val blockVSize: Double,
    @Json(name = "nTx") val nTx: Long,
    @Json(name = "totalFees") val totalFees: Long,
    @Json(name = "medianFee") val medianFee: Double,
    @Json(name = "feeRange") val feeRange: List<Double>,
)
