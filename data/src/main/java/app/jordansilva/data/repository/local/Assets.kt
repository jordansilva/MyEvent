package app.jordansilva.data.repository.local

import android.content.Context
import app.jordansilva.data.repository.remote.event.model.PlaceResponse
import app.jordansilva.data.repository.remote.event.model.TimeslotResponse
import app.jordansilva.infrastructure.util.extensions.fromJson
import com.google.gson.Gson
import java.nio.charset.Charset

class Assets(private val context: Context,
             private val gson: Gson) {

    fun readFile(filename: String): String {
        val inputStream = context.assets.open(filename)
        return inputStream.bufferedReader(Charset.forName("UTF-8")).use { it.readText() }
    }

    fun readPlaces(): List<PlaceResponse> {
        val json = readFile("places.json")
        return gson.fromJson(json)
    }

    fun readTimeslots(): List<TimeslotResponse> {
        val json = readFile("timeslots.json")
        //val timeslotsType = object : TypeToken<List<TimeslotResponse>>() {}.type
        //var timeslots = gson.fromJson<List<TimeslotResponse>>(json, timeslotsType)
        //timeslots = timeslots?.sortedWith(compareBy(TimeslotResponse::start, TimeslotResponse::end))

        return gson.fromJson(json)
    }
}