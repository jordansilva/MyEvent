package app.jordansilva.data.repository.local.mapper

import app.jordansilva.data.repository.local.model.Timeslot
import app.jordansilva.domain.domain.Agenda

object TimeslotToAgendaMapper {

    fun toAgenda(timeslot: Timeslot): Agenda {
        return Agenda(id = timeslot.id, name = timeslot.name, startDate = timeslot.start,
                endDate = timeslot.end, sections = arrayListOf())
    }

}