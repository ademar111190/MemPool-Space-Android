package mempool.space.model

import mempool.space.difficulty
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class DifficultyTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Difficulty>()
            .fromJson(readJson("response/difficulty.json"))
        assertEquals(difficulty, actual)
    }
}
