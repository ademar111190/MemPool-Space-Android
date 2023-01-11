package mempool.space.model

import mempool.space.moshiAdapter
import mempool.space.readJson
import mempool.space.weight
import org.junit.Assert.assertEquals
import org.junit.Test

class WeightTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Weight>()
            .fromJson(readJson("response/weight.json"))
        assertEquals(weight, actual)
    }
}
