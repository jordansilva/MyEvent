package app.jordansilva.data.repository.remote.event.model

import com.google.gson.annotations.SerializedName
import org.threeten.bp.OffsetDateTime
import java.util.*


data class TimeslotResponse(
        @SerializedName("_id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("start") val start: OffsetDateTime,
        @SerializedName("end") val end: OffsetDateTime,
        @SerializedName("parent") val parent: String?,
        @SerializedName("locations") val location: ArrayList<Location>?) {


    data class Location(@SerializedName("_id") val id: String,
                        @SerializedName("ordering") val ordering: Int)
}
