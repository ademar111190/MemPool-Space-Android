package mempool.space.api

import mempool.space.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MemPoolSpaceApi {

    @GET("/api/v1/difficulty-adjustment")
    suspend fun difficultyAdjustment(): Response<DifficultyAdjustment>

    // Address

    @GET("/api/address/{address}")
    suspend fun address(
        @Path("address") address: String,
    ): Response<Address>

    @GET("/api/address/{address}/txs")
    suspend fun addressTransactions(
        @Path("address") address: String,
    ): Response<List<Transaction>>

    @GET("/api/address/{address}/utxo")
    suspend fun addressUtxo(
        @Path("address") address: String,
    ): Response<List<AddressUtxo>>

    // Block

    @GET("/api/block/{blockHash}")
    suspend fun block(
        @Path("blockHash") blockHash: String,
    ): Response<Block>

    @GET("/api/block/{blockHash}/header")
    suspend fun blockHeader(
        @Path("blockHash") blockHash: String,
    ): Response<String>

    @GET("/api/block-height/{height}")
    suspend fun blockHeight(
        @Path("height") height: Long,
    ): Response<String>

    @GET("/api/block/{blockHash}/status")
    suspend fun blockStatus(
        @Path("blockHash") blockHash: String,
    ): Response<BlockStatus>

    @GET("/api/blocks/tip/height")
    suspend fun blocksTipHeight(): Response<Long>

    @GET("/api/blocks/tip/hash")
    suspend fun blocksTipHash(): Response<String>

    @GET("/api/block/{blockHash}/txid/{txIndex}")
    suspend fun blockTransactionId(
        @Path("blockHash") blockHash: String,
        @Path("txIndex") txid: Long,
    ): Response<String>

    @GET("/api/block/{blockHash}/txids")
    suspend fun blockTransactionsIds(
        @Path("blockHash") blockHash: String,
    ): Response<List<String>>

    @GET("/api/block/{blockHash}/txs")
    suspend fun blockTransactions(
        @Path("blockHash") blockHash: String,
    ): Response<List<Transaction>>

    // TODO continue on get blocks https://mempool.space/docs/api/rest#get-blocks

}
