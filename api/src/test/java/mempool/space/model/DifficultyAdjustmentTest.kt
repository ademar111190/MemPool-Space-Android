package mempool.space.model

import mempool.space.difficultyAdjustment
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class DifficultyAdjustmentTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<DifficultyAdjustment>()
            .fromJson(readJson("response/difficulty-adjustment.json"))
        assertEquals(difficultyAdjustment, actual)
    }
}
