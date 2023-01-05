package mempool.space.model

import mempool.space.block
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class BlockTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Block>()
            .fromJson(readJson("response/block.json"))
        assertEquals(block, actual)
    }
}
