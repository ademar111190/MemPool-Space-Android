package mempool.space.model

import mempool.space.mempoolBlock
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class MempoolBlockTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<MempoolBlock>()
            .fromJson(readJson("response/mempool-block.json"))
        assertEquals(mempoolBlock, actual)
    }
}
