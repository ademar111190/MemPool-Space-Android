package mempool.space.model

import mempool.space.*
import org.junit.Assert.assertEquals
import org.junit.Test

class PoolTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Pool>()
            .fromJson(readJson("response/pool.json"))
        assertEquals(pool, actual)
    }

    @Test
    fun jsonParse_miningPools() {
        val actual = moshiAdapter<Pool>()
            .fromJson(readJson("response/pool-mining-pools.json"))
        assertEquals(poolMiningPools, actual)
    }

    @Test
    fun jsonParse_miningPool() {
        val actual = moshiAdapter<Pool>()
            .fromJson(readJson("response/pool-mining-pool.json"))
        assertEquals(poolMiningPool, actual)
    }
}
