package sa.gov.absher.util

import android.content.Context
import android.content.Intent
import android.net.Uri

private var appContext: Context? = null

fun initContext(context: Context) {
    appContext = context.applicationContext
}

actual fun openUrl(url: String) {
    val context = appContext ?: return
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}
