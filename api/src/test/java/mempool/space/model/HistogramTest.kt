package mempool.space.model

import mempool.space.histogram
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class HistogramTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Histogram>()
            .fromJson(readJson("response/histogram.json"))
        assertEquals(histogram, actual)
    }
}
