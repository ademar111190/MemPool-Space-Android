package mempool.space.usecase

import dagger.Reusable
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CompletionHandler
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Reusable
class UrlChecker @Inject constructor() {
    // Just call one of the available apis and chceck if it is a success
    suspend fun isUrlValid(url: String): Boolean {
        val client = OkHttpClient()
        return try {
            val request = Request.Builder()
                .url("$url/api/v1/difficulty-adjustment")
                .build()
            val response = client.newCall(request).await()
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}

private suspend inline fun Call.await(): Response {
    return suspendCancellableCoroutine { continuation ->
        val callback = ContinuationCallback(this, continuation)
        enqueue(callback)
        continuation.invokeOnCancellation(callback)
    }
}

private class ContinuationCallback(
    private val call: Call,
    private val continuation: CancellableContinuation<Response>
) : Callback, CompletionHandler {

    override fun onResponse(call: Call, response: Response) {
        continuation.resume(response)
    }

    override fun onFailure(call: Call, e: IOException) {
        if (!call.isCanceled()) {
            continuation.resumeWithException(e)
        }
    }

    override fun invoke(cause: Throwable?) {
        try {
            call.cancel()
        } catch (_: Throwable) {
        }
    }
}
