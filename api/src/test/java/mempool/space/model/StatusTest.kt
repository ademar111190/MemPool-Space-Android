package mempool.space.model

import mempool.space.moshiAdapter
import mempool.space.readJson
import mempool.space.status
import org.junit.Assert.assertEquals
import org.junit.Test

class StatusTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Status>()
            .fromJson(readJson("response/status.json"))
        assertEquals(status, actual)
    }
}
