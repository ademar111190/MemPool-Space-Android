package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressUtxo(
    @Json(name = "txid") val txid: String,
    @Json(name = "vout") val vout: Long,
    @Json(name = "status") val status: Status,
    @Json(name = "value") val value: Long,
)
