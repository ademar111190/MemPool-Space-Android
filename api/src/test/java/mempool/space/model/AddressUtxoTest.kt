package mempool.space.model

import mempool.space.addressUtxo
import mempool.space.moshiListAdapter
import mempool.space.readJson
import org.junit.Assert.assertEquals
import org.junit.Test

class AddressUtxoTest {
    @Test
    fun jsonParse() {
        val actual = moshiListAdapter<AddressUtxo>()
            .fromJson(readJson("response/address-utxo.json"))
        assertEquals(addressUtxo, actual?.first())
    }
}
