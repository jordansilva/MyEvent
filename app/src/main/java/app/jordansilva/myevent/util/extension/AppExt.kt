package app.jordansilva.myevent.util.extension

import android.app.Application
import androidx.core.content.edit
import app.jordansilva.infrastructure.util.Constants
import org.jetbrains.anko.defaultSharedPreferences
import java.util.*

inline var Application.lastSynchronized: Date?
    get() {
        val date = defaultSharedPreferences.getLong(Constants.SHARED_PREFS.LAST_SYNC, 0L)
        return if (date > 0L) Date(date) else null
    }
    set(value) {
        defaultSharedPreferences.edit {
            value?.let {
                putLong(Constants.SHARED_PREFS.LAST_SYNC, value.time)
                apply()
            }
        }
    }