package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true) data class SizesWeights(
    @Json(name = "sizes") val sizes: List<Size>,
    @Json(name = "weights") val weights: List<Weight>,
)
