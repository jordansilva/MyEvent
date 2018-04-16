package app.jordansilva.data.repository.local.mapper

import app.jordansilva.data.repository.local.model.Timeslot
import app.jordansilva.domain.domain.AgendaSection

object TimeslotToAgendaSectionMapper {

    fun toAgendaSection(timeslot: Timeslot): AgendaSection {
        return AgendaSection(id = timeslot.id, name = timeslot.name, startDate = timeslot.start,
                endDate = timeslot.end, talks = arrayListOf(), parentId = timeslot.parent ?: "")
    }

}