package mempool.space

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.slf4j.LoggerFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun Any.readJson(
    file: String
) = this::class.java.classLoader!!.getResourceAsStream("json/$file")
    .bufferedReader().use { it.readText() }

fun moshi(): Moshi =
    Moshi.Builder().build()

inline fun <reified T> moshiAdapter(): JsonAdapter<T> =
    moshi().adapter(T::class.java)

inline fun <reified  T> moshiListAdapter(): JsonAdapter<List<T>> =
    moshi().adapter(Types.newParameterizedType(MutableList::class.java, T::class.java))

fun okHttpLogging(): HttpLoggingInterceptor =
    HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        private val log = LoggerFactory.getLogger("OkHttp")
        override fun log(message: String) {
            log.info(message)
        }
    }).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .readTimeout(1, TimeUnit.SECONDS)
    .connectTimeout(1, TimeUnit.SECONDS)
    .addInterceptor(okHttpLogging())
    .build()

fun retrofit(
    server: MockWebServer,
): Retrofit = Retrofit.Builder()
    .baseUrl(server.url("/"))
    .addConverterFactory(MoshiConverterFactory.create(moshi()).asLenient())
    .client(okHttpClient())
    .build()
