package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal
import java.math.BigInteger

@JsonClass(generateAdapter = true)
data class HashRateDifficulty(
    @Json(name = "hashrates") val hashRates: List<HashRate>,
    @Json(name = "difficulty") val difficulties: List<Difficulty>,
    @Json(name = "currentHashrate") val currentHashRate: BigInteger,
    @Json(name = "currentDifficulty") val currentDifficulty: BigDecimal,
)
