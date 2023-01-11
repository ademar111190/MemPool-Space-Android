package mempool.space.model

import mempool.space.blockFee
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class BlockFeeTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<BlockFee>()
            .fromJson(readJson("response/block-fee.json"))
        assertEquals(blockFee, actual)
    }
}
