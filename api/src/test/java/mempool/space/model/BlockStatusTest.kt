package mempool.space.model

import mempool.space.blockStatus
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class BlockStatusTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<BlockStatus>()
            .fromJson(readJson("response/block-status.json"))
        assertEquals(blockStatus, actual)
    }
}
