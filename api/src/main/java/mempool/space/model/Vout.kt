package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Vout(
    @Json(name = "scriptpubkey") val scriptpubkey: String,
    @Json(name = "scriptpubkey_asm") val scriptpubkeyAsm: String,
    @Json(name = "scriptpubkey_type") val scriptpubkeyType: String,
    @Json(name = "scriptpubkey_address") val scriptpubkeyAddress: String?,
    @Json(name = "value") val value: Long,
)
