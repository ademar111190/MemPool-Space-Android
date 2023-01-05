package mempool.space.model

import mempool.space.moshiAdapter
import mempool.space.readJson
import mempool.space.vout
import org.junit.Assert.assertEquals
import org.junit.Test

class VoutTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Vout>()
            .fromJson(readJson("response/vout.json"))
        assertEquals(vout, actual)
    }
}
