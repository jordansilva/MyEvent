package app.jordansilva.infrastructure.util.extensions

import app.jordansilva.infrastructure.util.Constants
import java.text.SimpleDateFormat
import java.util.*


fun Date.asString(format: SimpleDateFormat) = format.format(this)
fun Date.asString(format: String): String? {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = Constants.SETTINGS.TIMEZONE
    return asString(sdf)
}

fun Date.isBetween(start: Date, end: Date): Boolean {
    val startCalendar = Calendar.getInstance(Constants.SETTINGS.TIMEZONE)
    startCalendar.time = start

    val endCalendar = Calendar.getInstance(Constants.SETTINGS.TIMEZONE)
    endCalendar.time = end

    val currCalendar = Calendar.getInstance(Constants.SETTINGS.TIMEZONE)
    currCalendar.time = this

    return startCalendar.before(currCalendar) && endCalendar.after(currCalendar)
}

fun Date.isBetweenTime(start: Date, end: Date): Boolean {
    val startCalendar = instanceCalendar99(start, Constants.SETTINGS.TIMEZONE)
    val endCalendar = instanceCalendar99(end, Constants.SETTINGS.TIMEZONE)
    val currCalendar = instanceCalendar99(this, Constants.SETTINGS.TIMEZONE)

    return startCalendar.before(currCalendar) && endCalendar.after(currCalendar)
}

private fun instanceCalendar99(date : Date, timeZone: TimeZone) : Calendar {
    val calendar = Calendar.getInstance(timeZone)
    calendar.time = date
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    calendar.set(Calendar.MONTH, 1)
    calendar.set(Calendar.YEAR, 1999)
    return calendar
}