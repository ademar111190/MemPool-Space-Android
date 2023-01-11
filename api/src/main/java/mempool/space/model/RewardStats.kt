package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RewardStats(
    @Json(name = "startBlock") val startBlock: Long,
    @Json(name = "endBlock") val endBlock: Long,
    @Json(name = "totalReward") val totalReward: String,
    @Json(name = "totalFee") val totalFee: String,
    @Json(name = "totalTx") val totalTx: String,
)
