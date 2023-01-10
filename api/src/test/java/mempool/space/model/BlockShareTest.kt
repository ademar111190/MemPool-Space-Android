package mempool.space.model

import mempool.space.blockShare
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class BlockShareTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<BlockShare>()
            .fromJson(readJson("response/block-share.json"))
        assertEquals(blockShare, actual)
    }
}
