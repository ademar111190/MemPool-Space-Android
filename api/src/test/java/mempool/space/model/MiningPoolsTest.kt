package mempool.space.model

import mempool.space.miningPools
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class MiningPoolsTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<MiningPools>()
            .fromJson(readJson("response/mining-pools.json"))
        assertEquals(miningPools, actual)
    }
}
