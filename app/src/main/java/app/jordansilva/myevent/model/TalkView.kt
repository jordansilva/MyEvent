package app.jordansilva.myevent.model

import android.os.Parcelable
import app.jordansilva.infrastructure.util.extensions.asString
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class TalkView(val id: String, val title: String,
                    val description: String? = null,
                    val locations: List<String>?,
                    val startDate: Date,
                    val endDate: Date) : Parcelable {

    fun period() = "${startDate.asString("EEE")}, ${startDate.asString("HH:mm")} \u2014 ${endDate.asString("HH:mm")}"
    fun dayOfWeek() = startDate.asString("EEEE")
    fun durationPeriod() = "${startDate.asString("HH:mm")} \u2014 ${endDate.asString("HH:mm")}"
    fun places() = locations?.joinToString(", ")

    fun placesIsEmpty() : Boolean  {
        return places().isNullOrEmpty()
    }
}