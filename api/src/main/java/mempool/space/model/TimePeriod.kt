package mempool.space.model

enum class TimePeriod(
    private val raw: String,
) {
    ALL("all"),
    HOUR_24("24h"),
    DAY_3("3d"),
    WEEK_1("1w"),
    MONTH_1("1m"),
    MONTH_3("3m"),
    MONTH_6("6m"),
    YEAR_1("1y"),
    YEAR_2("2y"),
    YEAR_3("3y");

    override fun toString(): String = raw
}
