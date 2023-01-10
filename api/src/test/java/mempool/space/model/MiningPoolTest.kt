package mempool.space.model

import mempool.space.miningPool
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class MiningPoolTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<MiningPool>()
            .fromJson(readJson("response/mining-pool.json"))
        assertEquals(miningPool, actual)
    }
}
