package mempool.space.model

import mempool.space.moshiAdapter
import mempool.space.readJson
import mempool.space.vin
import org.junit.Assert.assertEquals
import org.junit.Test

class VinTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Vin>()
            .fromJson(readJson("response/vin.json"))
        assertEquals(vin, actual)
    }
}
