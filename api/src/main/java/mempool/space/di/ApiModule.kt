package mempool.space.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mempool.space.adapter.BigDecimalAdapter
import mempool.space.adapter.BigIntegerAdapter
import mempool.space.adapter.HistogramAdapter
import mempool.space.api.MemPoolSpaceApi
import mempool.space.storage.ApiPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.slf4j.LoggerFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object ApiModule {

    @[Provides Singleton]
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(BigDecimalAdapter)
        .add(BigIntegerAdapter)
        .add(HistogramAdapter)
        .add(KotlinJsonAdapterFactory())
        .build()

    @[Provides Singleton]
    fun providesMoshiConverterFactory(
        moshi: Moshi,
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi).asLenient()

    @[Provides Singleton]
    fun providesOkHttpLogging(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            private val log = LoggerFactory.getLogger("OkHttp")
            override fun log(message: String) {
                log.info(message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @[Provides Singleton]
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @[Provides Singleton]
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        apiPreferences: ApiPreferences,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiPreferences.baseUrl())
        .addConverterFactory(moshiConverterFactory)
        .client(okHttpClient)
        .build()

    @[Provides Singleton]
    fun providesMemPoolSpaceApi(
        retrofit: Retrofit,
    ): MemPoolSpaceApi = retrofit.create(MemPoolSpaceApi::class.java)

}
