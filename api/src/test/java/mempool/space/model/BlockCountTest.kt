package mempool.space.model

import mempool.space.blockCount
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class BlockCountTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<BlockCount>()
            .fromJson(readJson("response/block-count.json"))
        assertEquals(blockCount, actual)
    }
}
