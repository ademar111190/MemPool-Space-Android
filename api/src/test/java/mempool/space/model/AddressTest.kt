package mempool.space.model

import mempool.space.address
import mempool.space.moshiAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class AddressTest {
    @Test
    fun jsonParse() {
        val actual = moshiAdapter<Address>()
            .fromJson(readJson("response/address.json"))
        assertEquals(address, actual)
    }
}
