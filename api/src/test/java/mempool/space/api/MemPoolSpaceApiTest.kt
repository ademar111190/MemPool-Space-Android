package mempool.space.api

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mempool.space.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.create

@OptIn(ExperimentalCoroutinesApi::class)
class MemPoolSpaceApiTest {
    private lateinit var server: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var api: MemPoolSpaceApi

    @Before
    fun setUp() {
        server = MockWebServer()
        retrofit = retrofit(server)
        api = retrofit.create()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun difficultyAdjustment() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/difficulty-adjustment.json"))
        server.enqueue(response)
        val actual = api.difficultyAdjustment()
        assertEquals(difficultyAdjustment, actual.body())
    }

    // Address

    @Test
    fun address() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/address.json"))
        server.enqueue(response)
        val actual = api.address(rawAddress)
        assertEquals(address, actual.body())
    }

    @Test
    fun addressInvalid() = runTest {
        val errorBody = "Invalid Bitcoin address"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.address(rawAddress)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun addressTransactions() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/address-transactions.json"))
        server.enqueue(response)
        val actual = api.addressTransactions(rawAddress)
        assertEquals(listOf(addressTransaction), actual.body())
    }

    @Test
    fun addressTransactionsInvalid() = runTest {
        val errorBody = "Invalid Bitcoin address"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.addressTransactions(rawAddress)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun addressUtxo() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/address-utxo.json"))
        server.enqueue(response)
        val actual = api.addressUtxo(rawAddress)
        assertEquals(listOf(addressUtxo), actual.body())
    }

    @Test
    fun addressUtxoInvalid() = runTest {
        val errorBody = "Too many history entries"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.addressUtxo(rawAddress)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    // Block

    @Test
    fun block() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/block.json"))
        server.enqueue(response)
        val actual = api.block(blockHash)
        assertEquals(block, actual.body())
    }

    @Test
    fun blockInvalid() = runTest {
        val errorBody = "Invalid hex string"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.block("an invalid hash")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun blockHeader() = runTest {
        val blockHeader = "010000000000000000000000000000000000000000000000000000000000000000000000" +
                "3ba3edfd7a7b12b27ac72c3e67768f617fc81bc3888a51323a9fb8aa4b1e5e4a29ab5f49ffff001d1dac2b7c"
        val response = MockResponse().setResponseCode(200)
            .setBody(blockHeader)
        server.enqueue(response)
        val actual = api.blockHeader(blockHash)
        assertEquals(blockHeader, actual.body())
    }

    @Test
    fun blockHeaderInvalid() = runTest {
        val errorBody = "Invalid hex string"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.blockHeader("an invalid hash")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun blockHeight() = runTest {
        val blockHash = "000000000000000000067bea442af50a91377ac796e63b8d284354feff4042b3"
        val response = MockResponse().setResponseCode(200)
            .setBody(blockHash)
        server.enqueue(response)
        val actual = api.blockHeight(0)
        assertEquals(blockHash, actual.body())
    }

    @Test
    fun blockHeightInvalid() = runTest {
        val errorBody = "Block not found"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.blockHeight(-1)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun blockStatus() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/block-status.json"))
        server.enqueue(response)
        val actual = api.blockStatus(blockHash)
        assertEquals(blockStatus, actual.body())
    }

    @Test
    fun blockStatusInvalid() = runTest {
        val errorBody = "Invalid hex string"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.blockStatus("an invalid hash")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun blocksTipHeight() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody("770535")
        server.enqueue(response)
        val actual = api.blocksTipHeight()
        assertEquals(770535L, actual.body())
    }

    @Test
    fun blocksTipHash() = runTest {
        val tipHash = "000000000000000000024eafb317986c2214dd1200aa31fae6c500dfc27731bf"
        val response = MockResponse().setResponseCode(200)
            .setBody(tipHash)
        server.enqueue(response)
        val actual = api.blocksTipHash()
        assertEquals(tipHash, actual.body())
    }

    @Test
    fun blockTransactionId() = runTest {
        val txid = "4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b"
        val response = MockResponse().setResponseCode(200)
            .setBody(txid)
        server.enqueue(response)
        val actual = api.blockTransactionId(blockHash, 0)
        assertEquals(txid, actual.body())
    }

    @Test
    fun blockTransactionIdInvalid() = runTest {
        val errorBody = "tx index out of range"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.blockTransactionId("an invalid hash", 1)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun blockTransactionsIds() = runTest {
        val txid = "4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b"
        val response = MockResponse().setResponseCode(200)
            .setBody("[$txid]")
        server.enqueue(response)
        val actual = api.blockTransactionsIds(blockHash)
        assertEquals(listOf(txid), actual.body())
    }

    @Test
    fun blockTransactions() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/block-transactions.json"))
        server.enqueue(response)
        val actual = api.blockTransactions(blockHash)
        assertEquals(listOf(blockTransaction), actual.body())
    }

    @Test
    fun blockTransactionsInvalid() = runTest {
        val errorBody = "Invalid hex string"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.blockTransactions("an invalid hash")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

}
