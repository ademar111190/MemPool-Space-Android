package mempool.space.model

import mempool.space.hashRateDifficulty
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class HashRateDifficultyTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<HashRateDifficulty>()
            .fromJson(readJson("response/hash-rates-difficulty.json"))
        assertEquals(hashRateDifficulty, actual)
    }
}
