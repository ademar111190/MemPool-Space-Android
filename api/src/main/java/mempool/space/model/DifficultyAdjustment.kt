package mempool.space.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DifficultyAdjustment(
    @Json(name = "progressPercent") val progressPercent: Double,
    @Json(name = "difficultyChange") val difficultyChange: Double,
    @Json(name = "estimatedRetargetDate") val estimatedRetargetDate: Long,
    @Json(name = "remainingBlocks") val remainingBlocks: Long,
    @Json(name = "remainingTime") val remainingTime: Long,
    @Json(name = "previousRetarget") val previousRetarget: Double,
    @Json(name = "nextRetargetHeight") val nextRetargetHeight: Long,
    @Json(name = "timeAvg") val timeAvg: Long,
    @Json(name = "timeOffset") val timeOffset: Long,
)
