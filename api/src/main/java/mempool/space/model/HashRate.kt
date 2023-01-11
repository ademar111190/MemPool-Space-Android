package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigInteger

@JsonClass(generateAdapter = true)
data class HashRate(
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "avgHashrate") val avgHashRate: BigInteger,
    @Json(name = "share") val share: Double? = null,
    @Json(name = "poolName") val poolName: String? = null,
)
