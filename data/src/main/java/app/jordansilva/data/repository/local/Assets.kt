package app.jordansilva.data.repository.local

import android.content.Context
import app.jordansilva.domain.domain.Agenda
import com.google.gson.Gson
import org.json.JSONArray
import java.nio.charset.Charset
import java.util.*

class Assets(private val context: Context, private val gson: Gson) {

    fun readFile(filename: String): String {
        val inputStream = context.assets.open(filename)
        return inputStream.bufferedReader(Charset.forName("UTF-8")).use { it.readText() }
    }


    fun readPlaces(): HashMap<String, String> {
        val json = readFile("places.json")
        val jsonArray = JSONArray(json)

        val hashMap = HashMap<String, String>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            hashMap[jsonObject.getString("_id")] = jsonObject.getString("name")
        }

        return hashMap
    }


    fun readTimeslots(places: HashMap<String, String>): ArrayList<Agenda> {
//        val json = readFile("timeslots.json")
//
//        val timeslotsType = object : TypeToken<List<TimeslotResponse>>() {}.type
//        var timeslots = gson.fromJson<List<TimeslotResponse>>(json, timeslotsType)
//        timeslots = timeslots?.sortedWith(compareBy(TimeslotResponse::start, TimeslotResponse::end))
//
//        val agendas: ArrayList<Agenda> = arrayListOf()
//        val hashMap = HashMap<String, ArrayList<TimeslotResponse>>()
//
//        //O(N)
//        timeslots.forEach {
//            if (!it.parent.isNullOrBlank()) {
//                val parentId = it.parent!!
//                if (!hashMap.containsKey(parentId)) hashMap[parentId] = arrayListOf()
//                hashMap[parentId]!!.add(it)
//            } else
//                agendas.add(TimeslotMapper.toAgenda(it))
//        }
//
//        //TODO: Reduce complexity here!
//        agendas.forEach {
//            it.sections = hashMap[it.id]?.map(TimeslotMapper::toAgendaSection)
//
//            it.sections?.forEach { sec ->
//                sec.talks = hashMap[sec.id]?.map { TimeslotMapper.toTalk(it, places) }
//            }
//        }
//      return agendas
        return arrayListOf()

    }
}