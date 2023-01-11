package mempool.space.adapter

import com.squareup.moshi.FromJson
import mempool.space.model.Histogram

object HistogramAdapter {
    @FromJson fun fromJson(list: List<Double>): Histogram {
        return Histogram(list[0], list[1])
    }
}
