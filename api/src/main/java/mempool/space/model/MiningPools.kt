package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigInteger

@JsonClass(generateAdapter = true)
data class MiningPools(
    @Json(name = "lastEstimatedHashrate") val lastEstimatedHashRate: BigInteger,
    @Json(name = "blockCount") val blockCount: Long,
    @Json(name = "pools") val pools: List<Pool>,
)
