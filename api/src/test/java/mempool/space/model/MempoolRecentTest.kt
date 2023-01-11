package mempool.space.model

import mempool.space.mempoolRecent
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class MempoolRecentTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<MempoolRecent>()
            .fromJson(readJson("response/mempool-recent.json"))
        assertEquals(mempoolRecent, actual)
    }
}
