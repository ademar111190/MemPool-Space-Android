package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigInteger

@JsonClass(generateAdapter = true)
data class MiningPool(
    @Json(name = "estimatedHashrate") val estimatedHashRate: BigInteger,
    @Json(name = "blockShare") val blockShare: BlockShare,
    @Json(name = "blockCount") val blockCount: BlockCount,
    @Json(name = "pool") val pool: Pool,
)
