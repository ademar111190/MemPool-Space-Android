package mempool.space.storage

import android.content.SharedPreferences
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SimpleStorage @Inject constructor(
    private val prefs: SharedPreferences,
) {
    var memPoolUrl: String
        get() = prefs.getString(KEY_URL, DEFAULT_URL) ?: DEFAULT_URL
        set(value) = prefs.edit().putString(KEY_URL, value).apply()

    fun resetMemPoolUrl() {
        memPoolUrl = DEFAULT_URL
    }
}

private const val DEFAULT_URL = "https://mempool.space"
private const val KEY_URL = "memPoolUrl"
