package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stats(
    @Json(name= "funded_txo_count") val fundedTxoCount: Int,
    @Json(name= "funded_txo_sum") val fundedTxoSum: Int,
    @Json(name= "spent_txo_count") val spentTxoCount: Int,
    @Json(name= "spent_txo_sum") val spentTxoSum: Int,
    @Json(name= "tx_count") val txCount: Int,
)
