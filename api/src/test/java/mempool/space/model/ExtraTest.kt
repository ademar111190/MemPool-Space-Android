package mempool.space.model

import mempool.space.moshiAdapter
import mempool.space.readJson
import mempool.space.extra
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtraTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Extra>()
            .fromJson(readJson("response/extra.json"))
        assertEquals(extra, actual)
    }
}
