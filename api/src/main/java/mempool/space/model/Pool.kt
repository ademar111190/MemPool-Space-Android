package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pool(
    @Json(name = "name") val name: String,
    @Json(name = "slug") val slug: String,
    @Json(name = "id") val id: Long? = null,
    @Json(name = "poolId") val poolId: Long? = null,
    @Json(name = "link") val link: String? = null,
    @Json(name = "blockCount") val blockCount: Long? = null,
    @Json(name = "rank") val rank: Long? = null,
    @Json(name = "emptyBlocks") val emptyBlocks: Long? = null,
    @Json(name = "addresses") val addresses: List<String>? = null,
    @Json(name = "regexes") val regexes: List<String>? = null,
)
