package app.jordansilva.myevent.util.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v7.content.res.AppCompatResources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


fun getDrawable(context: Context, drawableId: Int): Drawable? {

    return when {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP -> VectorDrawableCompat.create(context.resources, drawableId, null)
        else -> AppCompatResources.getDrawable(context, drawableId)
    }
}