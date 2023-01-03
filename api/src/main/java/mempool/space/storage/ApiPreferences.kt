package mempool.space.storage

import android.content.SharedPreferences
import dagger.Reusable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Reusable class ApiPreferences @Inject constructor(
    private val prefs: SharedPreferences,
) {

    suspend fun readMemPoolUrl(): String = withContext(Dispatchers.IO) {
        prefs.getString(KEY_URL, DEFAULT_URL) ?: DEFAULT_URL
    }

    suspend fun updateMemPoolUrl(url: String) = withContext(Dispatchers.IO) {
        prefs.edit().putString(KEY_URL, url).apply()
    }

    suspend fun resetMemPoolUrl() = withContext(Dispatchers.IO) {
        prefs.edit().remove(KEY_URL).apply()
    }

}

private const val DEFAULT_URL = "https://mempool.space"
private const val KEY_URL = "memPoolUrl"
