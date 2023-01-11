package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MempoolRecent(
    @Json(name = "txid") val txId: String,
    @Json(name = "fee") val fee: Long,
    @Json(name = "vsize") val vSize: Long,
    @Json(name = "value") val value: Long,
)
