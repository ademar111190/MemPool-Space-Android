package mempool.space.model

import mempool.space.moshiAdapter
import mempool.space.readJson
import mempool.space.sizesWeights
import org.junit.Assert.assertEquals
import org.junit.Test

class SizesWeightsTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<SizesWeights>()
            .fromJson(readJson("response/sizes-weights.json"))
        assertEquals(sizesWeights, actual)
    }
}
