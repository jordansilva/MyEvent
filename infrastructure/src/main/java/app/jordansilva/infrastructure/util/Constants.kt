package app.jordansilva.infrastructure.util

import org.threeten.bp.ZoneId
import java.util.*

class Constants {

    object SETTINGS {
        val TIMEZONE = TimeZone.getTimeZone("EST")
        val ZONEID = ZoneId.of("EST")
    }

    object API {
        val GITHUB_URL = "https://raw.githubusercontent.com/jordansilva/MyEvent/master/util/"
    }

    object SHARED_PREFS {
        val LAST_SYNC = "LAST_SYNC"
    }
}

