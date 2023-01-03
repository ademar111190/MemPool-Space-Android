package mempool.space.usecase

import dagger.Reusable
import javax.inject.Inject

@Reusable
class UrlChecker @Inject constructor() {
    fun isUrlValid(url: String): Boolean {
        // TODO real check
        return url.startsWith("https://") || url.startsWith("http://")
    }
}
