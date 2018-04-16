package app.jordansilva.myevent.util.extension

import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.widget.TextView

inline fun TextView.drawableRight(drawableId: Int, color: Int? = null) {
    val drawable = getDrawable(context, drawableId)
    color?.let { drawable?.setColorFilter(ContextCompat.getColor(context, it), PorterDuff.Mode.SRC_IN) }
    setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
}

inline fun TextView.drawableLeft(drawableId: Int, color: Int? = null) {
    val drawable = getDrawable(context, drawableId)
    color?.let { drawable?.setColorFilter(ContextCompat.getColor(context, it), PorterDuff.Mode.SRC_IN) }
    setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}
