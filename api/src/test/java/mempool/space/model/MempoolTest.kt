package mempool.space.model

import mempool.space.mempool
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class MempoolTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Mempool>()
            .fromJson(readJson("response/mempool.json"))
        assertEquals(mempool, actual)
    }
}
