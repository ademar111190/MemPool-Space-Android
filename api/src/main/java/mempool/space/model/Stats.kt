package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stats(
    @Json(name= "funded_txo_count") val fundedTxoCount: Long,
    @Json(name= "funded_txo_sum") val fundedTxoSum: Long,
    @Json(name= "spent_txo_count") val spentTxoCount: Long,
    @Json(name= "spent_txo_sum") val spentTxoSum: Long,
    @Json(name= "tx_count") val txCount: Long,
)
