package app.jordansilva.data.mapper

import app.jordansilva.data.model.AgendaModel
import app.jordansilva.data.model.SectionModel
import app.jordansilva.data.model.TalkModel
import app.jordansilva.data.repository.remote.event.model.PlaceResponse
import app.jordansilva.data.repository.remote.event.model.TimeslotResponse
import java.util.*

object TimeslotMapper {

    fun toAgenda(timeslot: TimeslotResponse): AgendaModel {
        return AgendaModel(id = timeslot.id,
                name = timeslot.name,
                startDate = timeslot.start,
                endDate = timeslot.end)
    }


    fun toAgendaSection(timeslot: TimeslotResponse): SectionModel {
        return SectionModel(id = timeslot.id,
                name = timeslot.name,
                startDate = timeslot.start,
                endDate = timeslot.end,
                agendaId = timeslot.parent ?: "")
    }

    fun toTalk(timeslot: TimeslotResponse, places: HashMap<String, PlaceResponse>): TalkModel {
        return TalkModel(id = timeslot.id,
                name = timeslot.name,
                startDate = timeslot.start,
                endDate = timeslot.end,
                sectionId = timeslot.parent ?: "",
                locations = ArrayList(timeslot.location?.map { loc -> places[loc.id]?.name ?: "" })
        )
    }

}