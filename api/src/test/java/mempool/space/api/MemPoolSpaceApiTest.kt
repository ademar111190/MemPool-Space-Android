package mempool.space.api

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mempool.space.*
import mempool.space.model.TimePeriod
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
    fun getDifficultyAdjustment() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/difficulty-adjustment.json"))
        server.enqueue(response)
        val actual = api.getDifficultyAdjustment()
        assertEquals(difficultyAdjustment, actual.body())
    }

    // Address

    @Test
    fun getAddress() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/address.json"))
        server.enqueue(response)
        val actual = api.getAddress(rawAddress)
        assertEquals(address, actual.body())
    }

    @Test
    fun getAddressInvalid() = runTest {
        val errorBody = "Invalid Bitcoin address"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getAddress(rawAddress)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getAddressTransactions() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/address-transactions.json"))
        server.enqueue(response)
        val actual = api.getAddressTransactions(rawAddress)
        assertEquals(listOf(addressTransaction), actual.body())
    }

    @Test
    fun getAddressTransactionsInvalid() = runTest {
        val errorBody = "Invalid Bitcoin address"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getAddressTransactions(rawAddress)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getAddressTransactionsChain() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/address-transactions.json"))
        server.enqueue(response)
        val actual = api.getAddressTransactionsChain(rawAddress)
        assertEquals(listOf(addressTransaction), actual.body())
    }

    @Test
    fun getAddressTransactionsMempool() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/address-transactions.json"))
        server.enqueue(response)
        val actual = api.getAddressTransactionsMempool(rawAddress)
        assertEquals(listOf(addressTransaction), actual.body())
    }

    @Test
    fun getAddressUtxo() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/address-utxo.json"))
        server.enqueue(response)
        val actual = api.getAddressUtxo(rawAddress)
        assertEquals(listOf(addressUtxo), actual.body())
    }

    @Test
    fun getAddressUtxoInvalid() = runTest {
        val errorBody = "Too many history entries"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getAddressUtxo(rawAddress)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    // Block

    @Test
    fun getBlock() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/block.json"))
        server.enqueue(response)
        val actual = api.getBlock(blockHash)
        assertEquals(block, actual.body())
    }

    @Test
    fun getBlockInvalid() = runTest {
        val errorBody = "Invalid hex string"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getBlock("an invalid hash")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getBlockHeader() = runTest {
        val blockHeader = "010000000000000000000000000000000000000000000000000000000000000000000000" +
                "3ba3edfd7a7b12b27ac72c3e67768f617fc81bc3888a51323a9fb8aa4b1e5e4a29ab5f49ffff001d1dac2b7c"
        val response = MockResponse().setResponseCode(200)
            .setBody(blockHeader)
        server.enqueue(response)
        val actual = api.getBlockHeader(blockHash)
        assertEquals(blockHeader, actual.body())
    }

    @Test
    fun blockHeaderInvalid() = runTest {
        val errorBody = "Invalid hex string"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getBlockHeader("an invalid hash")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getBlockHeight() = runTest {
        val blockHash = "000000000000000000067bea442af50a91377ac796e63b8d284354feff4042b3"
        val response = MockResponse().setResponseCode(200)
            .setBody(blockHash)
        server.enqueue(response)
        val actual = api.getBlockHeight(0)
        assertEquals(blockHash, actual.body())
    }

    @Test
    fun getBlockHeightInvalid() = runTest {
        val errorBody = "Block not found"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getBlockHeight(-1)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getBlockStatus() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/block-status.json"))
        server.enqueue(response)
        val actual = api.getBlockStatus(blockHash)
        assertEquals(blockStatus, actual.body())
    }

    @Test
    fun getBlockStatusInvalid() = runTest {
        val errorBody = "Invalid hex string"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getBlockStatus("an invalid hash")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getBlockTipHeight() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody("770535")
        server.enqueue(response)
        val actual = api.getBlockTipHeight()
        assertEquals(770535L, actual.body())
    }

    @Test
    fun getBlockTipHash() = runTest {
        val tipHash = "000000000000000000024eafb317986c2214dd1200aa31fae6c500dfc27731bf"
        val response = MockResponse().setResponseCode(200)
            .setBody(tipHash)
        server.enqueue(response)
        val actual = api.getBlockTipHash()
        assertEquals(tipHash, actual.body())
    }

    @Test
    fun getBlockTransactionId() = runTest {
        val txid = "4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b"
        val response = MockResponse().setResponseCode(200)
            .setBody(txid)
        server.enqueue(response)
        val actual = api.getBlockTransactionId(blockHash, 0)
        assertEquals(txid, actual.body())
    }

    @Test
    fun getBlockTransactionIdInvalid() = runTest {
        val errorBody = "tx index out of range"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getBlockTransactionId("an invalid hash", 1)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getBlockTransactionIds() = runTest {
        val txid = "4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b"
        val response = MockResponse().setResponseCode(200)
            .setBody("[$txid]")
        server.enqueue(response)
        val actual = api.getBlockTransactionIds(blockHash)
        assertEquals(listOf(txid), actual.body())
    }

    @Test
    fun getBlockTransactions() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/block-transactions.json"))
        server.enqueue(response)
        val actual = api.getBlockTransactions(blockHash)
        assertEquals(listOf(blockTransaction), actual.body())
    }

    @Test
    fun getBlockTransactionsInvalid() = runTest {
        val errorBody = "Invalid hex string"
        val response = MockResponse().setResponseCode(400)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getBlockTransactions("an invalid hash")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getBlocks() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/blocks.json"))
        server.enqueue(response)
        val actual = api.getBlocks(0)
        assertEquals(listOf(blockPlusExtra), actual.body())
    }

    @Test
    fun getBlocksInvalid() = runTest {
        // sending 228928298292823 as arg give an answer of 500 and a message of
        // 404, probably a bug on mempool.space
        val errorBody = "Request failed with status code 404"
        val response = MockResponse().setResponseCode(500)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getBlocks(228928298292823)
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    // Mining

    @Test
    fun getMiningPools() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/mining-pools.json"))
        server.enqueue(response)
        val actual = api.getMiningPools(TimePeriod.HOUR_24)
        assertEquals(miningPools, actual.body())
    }

    @Test
    fun getMiningPool() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/mining-pool.json"))
        server.enqueue(response)
        val actual = api.getMiningPool("foundryusa")
        assertEquals(miningPool, actual.body())
    }

    @Test
    fun getMiningPoolInvalid() = runTest {
        val errorBody = "This mining pool does not exist 'an invalid pool'"
        val response = MockResponse().setResponseCode(404)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getMiningPool("an invalid pool")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getMiningPoolHashRates() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/hash-rates.json"))
        server.enqueue(response)
        val actual = api.getMiningPoolHashRates(TimePeriod.MONTH_1)
        assertEquals(listOf(hashRate), actual.body())
    }

    @Test
    fun getMiningPoolHashRate() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/hash-rates.json"))
        server.enqueue(response)
        val actual = api.getMiningPoolHashRate("a-valid-slug")
        assertEquals(listOf(hashRate), actual.body())
    }

    @Test
    fun getMiningPoolHashRateInvalid() = runTest {
        val errorBody = "This mining pool does not exist 'an invalid pool'"
        val response = MockResponse().setResponseCode(404)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getMiningPoolHashRate("an invalid pool")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

    @Test
    fun getMiningPoolBlocks() = runTest {
        val response = MockResponse().setResponseCode(200)
            .setBody(readJson("response/blocks.json"))
        server.enqueue(response)
        val actual = api.getMiningPoolBlocks("a-valid-slug")
        assertEquals(listOf(blockPlusExtra), actual.body())
    }

    @Test
    fun getMiningPoolBlocksInvalid() = runTest {
        val errorBody = "This mining pool does not exist 'an invalid pool'"
        val response = MockResponse().setResponseCode(404)
            .setBody(errorBody)
        server.enqueue(response)
        val actual = api.getMiningPoolBlocks("an invalid pool")
        assertEquals(errorBody, actual.errorBody()?.string())
    }

}
