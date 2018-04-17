package app.jordansilva.myevent.util.extension

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v7.content.res.AppCompatResources
import android.text.format.DateUtils
import java.util.*


fun getDrawable(context: Context, drawableId: Int): Drawable? {

    return when {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP -> VectorDrawableCompat.create(context.resources, drawableId, null)
        else -> AppCompatResources.getDrawable(context, drawableId)
    }
}

val Int.dp: Float
    get() = (this / Resources.getSystem().displayMetrics.density)

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Date.relativeTime: String
    get() = DateUtils.getRelativeTimeSpanString(this.time, Calendar.getInstance(Locale.getDefault()).timeInMillis, 0).toString()

fun Number.format(format: String): String {
    return String.format(Locale.getDefault(), format, this)
}