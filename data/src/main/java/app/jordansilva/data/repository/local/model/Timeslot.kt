package app.jordansilva.data.repository.local.model

import com.google.gson.annotations.SerializedName
import java.util.*


data class Timeslot(
        @SerializedName("_id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("start") val start: Date,
        @SerializedName("end") val end: Date,
        @SerializedName("parent") val parent: String?,
        @SerializedName("locations") val location: ArrayList<Location>?) {


    data class Location(@SerializedName("_id") val id: String,
                        @SerializedName("ordering") val ordering: Int)
}