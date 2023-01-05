package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Vin(
    @Json(name = "txid") val txid: String,
    @Json(name = "vout") val vout: Long,
    @Json(name = "prevout") val prevout: Vout?,
    @Json(name = "scriptsig") val scriptsig: String,
    @Json(name = "scriptsig_asm") val scriptsigAsm: String,
    @Json(name = "witness") val witness: List<String>?,
    @Json(name = "is_coinbase") val isCoinbase: Boolean,
    @Json(name = "sequence") val sequence: Long,
)
