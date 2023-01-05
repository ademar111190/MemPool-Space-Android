package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlockStatus(
    @Json(name = "in_best_chain") val inBestChain: Boolean,
    @Json(name = "height") val height: Long,
    @Json(name = "next_best") val nextBest: String,
)
