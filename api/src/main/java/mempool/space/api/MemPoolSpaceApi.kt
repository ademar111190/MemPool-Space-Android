package mempool.space.api

import mempool.space.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MemPoolSpaceApi {

    // https://mempool.space/docs/api/rest#get-difficulty-adjustment
    @GET("/api/v1/difficulty-adjustment")
    suspend fun getDifficultyAdjustment(): Response<DifficultyAdjustment>

    // Address

    // https://mempool.space/docs/api/rest#get-address
    @GET("/api/address/{address}")
    suspend fun getAddress(
        @Path("address") address: String,
    ): Response<Address>

    // https://mempool.space/docs/api/rest#get-address-transactions
    @GET("/api/address/{address}/txs")
    suspend fun getAddressTransactions(
        @Path("address") address: String,
    ): Response<List<Transaction>>

    // https://mempool.space/docs/api/rest#get-address-transactions-chain
    @GET("/api/address/{address}/txs/chain")
    suspend fun getAddressTransactionsChain(
        @Path("address") address: String,
    ): Response<List<Transaction>>

    // https://mempool.space/docs/api/rest#get-address-transactions-mempool
    @GET("/api/address/{address}/txs/mempool")
    suspend fun getAddressTransactionsMempool(
        @Path("address") address: String,
    ): Response<List<Transaction>>

    // https://mempool.space/docs/api/rest#get-address-utxo
    @GET("/api/address/{address}/utxo")
    suspend fun getAddressUtxo(
        @Path("address") address: String,
    ): Response<List<AddressUtxo>>

    // Block

    // https://mempool.space/docs/api/rest#get-block
    @GET("/api/block/{blockHash}")
    suspend fun getBlock(
        @Path("blockHash") blockHash: String,
    ): Response<Block>

    // https://mempool.space/docs/api/rest#get-block-header
    @GET("/api/block/{blockHash}/header")
    suspend fun getBlockHeader(
        @Path("blockHash") blockHash: String,
    ): Response<String>

    // https://mempool.space/docs/api/rest#get-block-height
    @GET("/api/block-height/{height}")
    suspend fun getBlockHeight(
        @Path("height") height: Long,
    ): Response<String>

    // https://mempool.space/docs/api/rest#get-block-status
    @GET("/api/block/{blockHash}/status")
    suspend fun getBlockStatus(
        @Path("blockHash") blockHash: String,
    ): Response<BlockStatus>

    // https://mempool.space/docs/api/rest#get-block-tip-height
    @GET("/api/blocks/tip/height")
    suspend fun getBlockTipHeight(): Response<Long>

    // https://mempool.space/docs/api/rest#get-block-tip-hash
    @GET("/api/blocks/tip/hash")
    suspend fun getBlockTipHash(): Response<String>

    // https://mempool.space/docs/api/rest#get-block-transaction-id
    @GET("/api/block/{blockHash}/txid/{txIndex}")
    suspend fun getBlockTransactionId(
        @Path("blockHash") blockHash: String,
        @Path("txIndex") txid: Long,
    ): Response<String>

    // https://mempool.space/docs/api/rest#get-block-transaction-ids
    @GET("/api/block/{blockHash}/txids")
    suspend fun getBlockTransactionIds(
        @Path("blockHash") blockHash: String,
    ): Response<List<String>>

    // https://mempool.space/docs/api/rest#get-block-transactions
    @GET("/api/block/{blockHash}/txs")
    suspend fun getBlockTransactions(
        @Path("blockHash") blockHash: String,
    ): Response<List<Transaction>>

    // https://mempool.space/docs/api/rest#get-blocks
    @GET("/api/v1/blocks/{height}")
    suspend fun getBlocks(
        @Path("height") height: Long,
    ): Response<List<Block>>

    // Mining

    // https://mempool.space/docs/api/rest#get-mining-pools
    @GET("/api/v1/mining/pools/{timePeriod}")
    suspend fun getMiningPools(
        @Path("timePeriod") timePeriod: TimePeriod,
    ): Response<MiningPools>

    // https://mempool.space/docs/api/rest#get-mining-pool
    @GET("/api/v1/mining/pool/{slug}")
    suspend fun getMiningPool(
        @Path("slug") slug: String,
    ): Response<MiningPool>

    // https://mempool.space/docs/api/rest#get-mining-pool-hashrates
    @GET("/api/v1/mining/hashrate/pools/{timePeriod}")
    suspend fun getMiningPoolHashRates(
        @Path("timePeriod") timePeriod: TimePeriod,
    ): Response<List<HashRate>>

    // https://mempool.space/docs/api/rest#get-mining-pool-hashrate
    @GET("/api/v1/mining/pool/{slug}/hashrate")
    suspend fun getMiningPoolHashRate(
        @Path("slug") slug: String,
    ): Response<List<HashRate>>

    // https://mempool.space/docs/api/rest#get-mining-pool-blocks
    @GET("/api/v1/mining/pool/{slug}/blocks")
    suspend fun getMiningPoolBlocks(
        @Path("slug") slug: String,
    ): Response<List<Block>>

}
