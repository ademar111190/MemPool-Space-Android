package mempool.space.model

import mempool.space.hashRate
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class HashRateTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<HashRate>()
            .fromJson(readJson("response/hash-rate.json"))
        assertEquals(hashRate, actual)
    }
}
