package mempool.space.activity

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Context.getActivity(): Activity {
    if (this is Activity) {
        return this
    } else if (this is ContextWrapper) {
        return this.baseContext.getActivity()
    }
    throw IllegalStateException("Context is not an Activity")
}
