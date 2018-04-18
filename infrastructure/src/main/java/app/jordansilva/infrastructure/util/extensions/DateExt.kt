package app.jordansilva.infrastructure.util.extensions

import app.jordansilva.infrastructure.util.Constants
import org.threeten.bp.DateTimeUtils
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

fun LocalDateTime.toDate(offset: ZoneOffset = ZoneOffset.UTC) = DateTimeUtils.toDate(this.toInstant(offset))

fun LocalDateTime.asString(format: DateTimeFormatter) = format.format(this)
fun LocalDateTime.asString(format: String): String? {
    val sdf = DateTimeFormatter.ofPattern(format, Locale.getDefault())
    sdf.withZone(Constants.SETTINGS.ZONEID)

    return asString(sdf)
}

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

fun Date.differenceInDays(date: Date) : Int {
    val diff = date.time - time
    return (diff / (24 * 60 * 60 * 1000)).toInt()
}

fun Date.isBetweenIngoreTime(start: Date, end: Date): Boolean {
    val startCalendar = instanceCalendar24h(start, Constants.SETTINGS.TIMEZONE, 0, 0, 0)
    val endCalendar = instanceCalendar24h(end, Constants.SETTINGS.TIMEZONE, 23, 59, 59)
    val currCalendar = instanceCalendar24h(this, Constants.SETTINGS.TIMEZONE, 12, 0, 0)

    return startCalendar.before(currCalendar) && endCalendar.after(currCalendar)
}

fun Date.isBetweenIgnoreDate(start: Date, end: Date): Boolean {
    val startCalendar = instanceCalendar99(start, Constants.SETTINGS.TIMEZONE)
    val endCalendar = instanceCalendar99(end, Constants.SETTINGS.TIMEZONE)
    val currCalendar = instanceCalendar99(this, Constants.SETTINGS.TIMEZONE)

    return startCalendar.before(currCalendar) && endCalendar.after(currCalendar)
}

private fun instanceCalendar99(date: Date, timeZone: TimeZone) = Calendar.getInstance(timeZone).apply {
    time = date
    set(1999, 1, 1)
}

private fun instanceCalendar24h(date: Date, timeZone: TimeZone, hour: Int, minutes: Int, seconds: Int) =
        Calendar.getInstance(timeZone).apply {
            time = date
            hourOfDay = hour
            minute = minutes
            second = seconds
            millisecond = 0
        }