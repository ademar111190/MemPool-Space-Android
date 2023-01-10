package mempool.space

import mempool.space.model.*
import java.math.BigInteger

val difficultyAdjustment = DifficultyAdjustment(
    progressPercent = 19.444444444444446,
    difficultyChange = 9.084331649761367,
    estimatedRetargetDate = 1673828636592,
    remainingBlocks = 1624,
    remainingTime = 893253592,
    previousRetarget = -3.592617372757624,
    nextRetargetHeight = 772128,
    timeAvg = 550033,
    timeOffset = 0,
)

val mempoolStats = Stats(
    fundedTxoCount = 0,
    fundedTxoSum = 0,
    spentTxoCount = 0,
    spentTxoSum = 0,
    txCount = 0,
)

val chainStats = Stats(
    fundedTxoCount = 3633,
    fundedTxoSum = 1855994889,
    spentTxoCount = 0,
    spentTxoSum = 0,
    txCount = 3632,
)

const val rawAddress = "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"

val address = Address(
    address = rawAddress,
    chainStats = chainStats,
    mempoolStats = mempoolStats,
)

val status = Status(
    confirmed = true,
    blockHeight = 770454,
    blockHash = "00000000000000000003aaa849ceeef60981437108b73c8adec1daf53ad28183",
    blockTime = 1672904287,
)

val vout = Vout(
    scriptpubkey = "0014c99fd000fb30137ae03fd2b28f52878e9b29194f",
    scriptpubkeyAsm = "OP_0 OP_PUSHBYTES_20 c99fd000fb30137ae03fd2b28f52878e9b29194f",
    scriptpubkeyType = "v0_p2wpkh",
    scriptpubkeyAddress = "bc1qex0aqq8mxqfh4cpl62eg755836djjx20yzuuu8",
    value = 46093,
)

val vin = Vin(
    txid = "53719514a78caa6aebfed384e7b256fa9ed06f8ee949105635ca6dd348e4026d",
    vout = 0,
    prevout = Vout(
        scriptpubkey = "0014c99fd000fb30137ae03fd2b28f52878e9b29194f",
        scriptpubkeyAsm = "OP_0 OP_PUSHBYTES_20 c99fd000fb30137ae03fd2b28f52878e9b29194f",
        scriptpubkeyType = "v0_p2wpkh",
        scriptpubkeyAddress = "bc1qex0aqq8mxqfh4cpl62eg755836djjx20yzuuu8",
        value = 46798,
    ),
    scriptsig = "",
    scriptsigAsm = "",
    witness = listOf(
        "304402205ecc670d1f9f73a14d9a1bd79e93da3a9b56fd61a2df787cc3ce58f2d2c8f373022017d56413d533851cdb4e2824f4b0cfab6eb62e3486e19f3a6133991f6342684801",
        "031f6fa906bb52f3e1bdc59156a5659ce1aa251eaf26f411413c76409360ef7205",
    ),
    isCoinbase = false,
    sequence = 4294967293,
)

val addressTransaction = Transaction(
    txid = "79d3963ea91364a57002ef23cc8274cd38a0d2b4769de92722b78b39094fea70",
    version = 2,
    locktime = 770453,
    vin = listOf(vin),
    vout = listOf(
        vout, Vout(
            scriptpubkey = "76a91462e907b15cbf27d5425399ebf6f0fb50ebb88f1888ac",
            scriptpubkeyAsm = "OP_DUP OP_HASH160 OP_PUSHBYTES_20 62e907b15cbf27d5425399ebf6f0fb50ebb88f18 OP_EQUALVERIFY OP_CHECKSIG",
            scriptpubkeyType = "p2pkh",
            scriptpubkeyAddress = "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa",
            value = 558
        )
    ),
    size = 225,
    weight = 573,
    fee = 147,
    status = status,
)

val addressUtxo = AddressUtxo(
    txid = "e1897fbb5d07e28d5e637afa2b8ffca49ad990e2932aefd32819ac2d8888027c",
    vout = 0,
    status = Status(
        confirmed = true,
        blockHeight = 770365,
        blockHash = "00000000000000000007047412d43cf78881a9645dcb1e9c9e875b6c273b2cae",
        blockTime = 1672856459,
    ),
    value = 644069114,
)

val pool = Pool(
    id = 137,
    name = "Unknown",
    slug = "unknown",
)

val poolMiningPools = Pool(
    poolId = 111,
    name = "Foundry USA",
    link = "https://foundrydigital.com/",
    blockCount = 48,
    rank = 1,
    emptyBlocks = 0,
    slug = "foundryusa",
)

val poolMiningPool = Pool(
    id = 111,
    name = "Foundry USA",
    link = "https://foundrydigital.com/",
    slug = "foundryusa",
    addresses = listOf(
        "1FFxkVijzvUPUeHgkFjBk2Qw8j3wQY2cDw",
        "12KKDt4Mj7N5UAkQMN7LtPZMayenXHa8KL",
        "bc1qxhmdufsvnuaaaer4ynz88fspdsxq2h9e9cetdj",
    ),
    regexes = listOf(
        "Foundry USA Pool",
        "/2cDw/",
    ),
)

val extra = Extra(
    coinbaseRaw = "04ffff001d0104455468652054696d65732030332f4a616e2f32303039204368616e63656c6c6f72206f6e206272696e6b206f66207365636f6e64206261696c6f757420666f722062616e6b73",
    medianFee = 0,
    feeRange = listOf(0, 0, 0, 0, 0, 0, 0),
    reward = 5000000000,
    totalFees = 0,
    averageFee = 0,
    averageFeeRate = 0,
    pool = pool,
)

const val blockHash = "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f"

val block = Block(
    id = blockHash,
    height = 0,
    version = 1,
    timestamp = 1231006505,
    txCount = 1,
    size = 285,
    weight = 1140,
    merkleRoot = "4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b",
    previousblockhash = null,
    mediantime = 1231006505,
    nonce = 2083236893,
    bits = 486604799,
    difficulty = 1,
    extra = null,
)

val blockPlusExtra = block.copy(
    extra = extra,
    mediantime = null,
)

val blockStatus = BlockStatus(
    inBestChain = true,
    height = 0,
    nextBest = "00000000839a8e6886ab5951d76f411475428afc90947ee320161bbf18eb6048",
)

val blockTransaction = Transaction(
    txid = "4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b",
    version = 1,
    locktime = 0,
    vin = listOf(
        Vin(
            txid = "0000000000000000000000000000000000000000000000000000000000000000",
            vout = 4294967295,
            prevout = null,
            scriptsig = "04ffff001d0104455468652054696d65732030332f4a616e2f32303039204368616e63656c6c6f72206f6e206272696e6b206f66207365636f6e64206261696c6f757420666f722062616e6b73",
            scriptsigAsm = "OP_PUSHBYTES_4 ffff001d OP_PUSHBYTES_1 04 OP_PUSHBYTES_69 5468652054696d65732030332f4a616e2f32303039204368616e63656c6c6f72206f6e206272696e6b206f66207365636f6e64206261696c6f757420666f722062616e6b73",
            isCoinbase = true,
            sequence = 4294967295,
            witness = null,
        ),
    ),
    vout = listOf(
        Vout(
            scriptpubkey = "4104678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5fac",
            scriptpubkeyAsm = "OP_PUSHBYTES_65 04678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f OP_CHECKSIG",
            scriptpubkeyType = "p2pk",
            scriptpubkeyAddress = null,
            value = 5000000000,
        ),
    ),
    size = 204,
    weight = 816,
    fee = 0,
    status = Status(
        confirmed = true,
        blockHeight = 0,
        blockHash = "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f",
        blockTime = 1231006505,
    ),
)

val miningPools = MiningPools(
    lastEstimatedHashRate = BigInteger("284421401046070800000"),
    blockCount = 168,
    pools = listOf(poolMiningPools),
)

val blockCount = BlockCount(
    hour24 = 48,
    week1 = 322,
    all = 15932,
)

val blockShare = BlockShare(
    hour24 = 0.2891566265060241,
    week1 = 0.29193109700815956,
    all = 0.02065558000147799,
)

val miningPool = MiningPool(
    estimatedHashRate = BigInteger("82053492127869000000"),
    blockShare = blockShare,
    blockCount = blockCount,
    pool = poolMiningPool,
)

val hashRate = HashRate(
    timestamp = 1670803200,
    avgHashRate = BigInteger("64172430960019090000"),
    share = 0.243243,
    poolName = "Foundry USA"
)
