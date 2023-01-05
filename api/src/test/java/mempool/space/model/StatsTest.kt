package mempool.space.model

import mempool.space.chainStats
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class StatsTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Stats>()
            .fromJson(readJson("response/stats.json"))
        assertEquals(chainStats, actual)
    }
}
