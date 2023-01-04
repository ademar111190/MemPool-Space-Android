package mempool.space.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.biometric.BiometricManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object SingletonModule {

    @[Provides Singleton]
    fun providesSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences = context.getSharedPreferences("mempool.space", Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun providesResources(
        @ApplicationContext context: Context,
    ): Resources = context.resources

    @[Provides Singleton]
    fun providesBiometricManager(
        @ApplicationContext context: Context,
    ): BiometricManager = BiometricManager.from(context)

}
