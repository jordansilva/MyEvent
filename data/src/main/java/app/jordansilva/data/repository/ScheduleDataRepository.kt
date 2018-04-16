package app.jordansilva.data.repository

import app.jordansilva.data.repository.local.Assets
import app.jordansilva.data.repository.local.mapper.TimeslotToAgendaMapper
import app.jordansilva.data.repository.local.mapper.TimeslotToAgendaSectionMapper
import app.jordansilva.data.repository.local.mapper.TimeslotToTalkMapper
import app.jordansilva.data.repository.local.model.Timeslot
import app.jordansilva.domain.domain.Agenda
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.repository.ScheduleRepository
import app.jordansilva.infrastructure.util.extensions.isBetweenTime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.util.*

class ScheduleDataRepository constructor(private val assets: Assets) : ScheduleRepository {

    override fun getTalksByDate(date: Date): List<Talk> {
        val listAgenda = getAgenda()

        val talks = ArrayList<Talk>()
        listAgenda.forEach {
            it.sections?.forEach {
                val items = it.talks?.filter { date.isBetweenTime(it.startDate, it.endDate) }
                items?.let { talks.addAll(items) }
            }
        }

        return talks
    }

    override fun getAgenda(): List<Agenda> {
        val places = readPlaces()
        val agenda = readTimeslots(places)

        return agenda
    }

    private fun readPlaces(): HashMap<String, String> {
        val json = assets.readFile("places.json")
        val jsonArray = JSONArray(json)

        val hashMap = HashMap<String, String>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            hashMap[jsonObject.getString("_id")] = jsonObject.getString("name")
        }

        return hashMap
    }


    private fun readTimeslots(places: HashMap<String, String>): ArrayList<Agenda> {
        val json = assets.readFile("timeslots.json")

        val timeslotsType = object : TypeToken<List<Timeslot>>() {}.type
        var timeslots = Gson().fromJson<List<Timeslot>>(json, timeslotsType)
        timeslots = timeslots?.sortedWith(compareBy(Timeslot::start, Timeslot::end))

        val agendas: ArrayList<Agenda> = arrayListOf()
        val hashMap = HashMap<String, ArrayList<Timeslot>>()

        //O(N)
        timeslots.forEach {
            if (!it.parent.isNullOrBlank()) {
                val parentId = it.parent!!
                if (!hashMap.containsKey(parentId)) hashMap[parentId] = arrayListOf()
                hashMap[parentId]!!.add(it)
            } else
                agendas.add(TimeslotToAgendaMapper.toAgenda(it))
        }

        //TODO: Reduce complexity here!
        agendas.forEach {
            it.sections = hashMap[it.id]?.map(TimeslotToAgendaSectionMapper::toAgendaSection)

            it.sections?.forEach { sec ->
                sec.talks = hashMap[sec.id]?.map { TimeslotToTalkMapper.toTalk(it, places) }
            }
        }

        return agendas

    }

}