package mempool.space.model

import mempool.space.moshiAdapter
import mempool.space.readJson
import mempool.space.rewardStats
import org.junit.Assert.assertEquals
import org.junit.Test

class RewardStatsTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<RewardStats>()
            .fromJson(readJson("response/reward-stats.json"))
        assertEquals(rewardStats, actual)
    }
}
