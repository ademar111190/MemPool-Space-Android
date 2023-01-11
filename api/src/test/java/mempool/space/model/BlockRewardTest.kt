package mempool.space.model

import mempool.space.blockReward
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class BlockRewardTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<BlockReward>()
            .fromJson(readJson("response/block-reward.json"))
        assertEquals(blockReward, actual)
    }
}
