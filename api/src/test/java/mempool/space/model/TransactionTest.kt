package mempool.space.model

import mempool.space.addressTransaction
import mempool.space.blockTransaction
import mempool.space.moshiListAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class TransactionTest {
    @Test
    fun jsonParse_address() {
        val actual = moshiListAdapter<Transaction>()
            .fromJson(readJson("response/address-transactions.json"))
        assertEquals(listOf(addressTransaction), actual)
    }

    @Test
    fun jsonParse_block() {
        val actual = moshiListAdapter<Transaction>()
            .fromJson(readJson("response/block-transactions.json"))
        assertEquals(listOf(blockTransaction), actual)
    }
}
