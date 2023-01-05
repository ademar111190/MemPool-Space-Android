package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Transaction(
    @Json(name = "txid") val txid: String,
    @Json(name = "version") val version: Long,
    @Json(name = "locktime") val locktime: Long,
    @Json(name = "vin") val vin: List<Vin>,
    @Json(name = "vout") val vout: List<Vout>,
    @Json(name = "size") val size: Long,
    @Json(name = "weight") val weight: Long,
    @Json(name = "fee") val fee: Long,
    @Json(name = "status") val status: Status,
)
