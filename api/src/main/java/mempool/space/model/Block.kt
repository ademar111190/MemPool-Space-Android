package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Block(
    @Json(name = "id") val id: String,
    @Json(name = "height") val height: Long,
    @Json(name = "version") val version: Long,
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "tx_count") val txCount: Long,
    @Json(name = "size") val size: Long,
    @Json(name = "weight") val weight: Long,
    @Json(name = "merkle_root") val merkleRoot: String,
    @Json(name = "previousblockhash") val previousblockhash: String?,
    @Json(name = "mediantime") val mediantime: Long,
    @Json(name = "nonce") val nonce: Long,
    @Json(name = "bits") val bits: Long,
    @Json(name = "difficulty") val difficulty: Long,
)
