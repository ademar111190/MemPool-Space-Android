package mempool.space.model

import mempool.space.feeRate
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class FeeRateTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<FeeRate>()
            .fromJson(readJson("response/fee-rate.json"))
        assertEquals(feeRate, actual)
    }
}
