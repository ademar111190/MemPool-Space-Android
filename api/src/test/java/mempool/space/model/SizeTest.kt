package mempool.space.model

import mempool.space.moshiAdapter
import mempool.space.readJson
import mempool.space.size
import org.junit.Assert.assertEquals
import org.junit.Test

class SizeTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Size>()
            .fromJson(readJson("response/size.json"))
        assertEquals(size, actual)
    }
}
