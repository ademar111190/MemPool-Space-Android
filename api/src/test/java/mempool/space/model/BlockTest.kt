package mempool.space.model

import mempool.space.*
import org.junit.Assert.assertEquals
import org.junit.Test

class BlockTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Block>()
            .fromJson(readJson("response/block.json"))
        assertEquals(block, actual)
    }

    @Test
    fun jsonParse_plusExtra() {
        val actual = moshiListAdapter<Block>()
            .fromJson(readJson("response/blocks.json"))
        assertEquals(listOf(blockPlusExtra), actual)
    }
}
