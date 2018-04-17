package app.jordansilva.data.mapper

import app.jordansilva.data.model.AgendaModel
import app.jordansilva.domain.domain.Agenda

object AgendaMapper : Mapper<Agenda, AgendaModel> {

    override fun mapFromDomain(type: Agenda): AgendaModel {
        return AgendaModel(id = type.id, name = type.name, startDate = type.startDate, endDate = type.endDate)
    }

    override fun mapToDomain(type: AgendaModel): Agenda {
        return Agenda(id = type.id, name = type.name, startDate = type.startDate, endDate = type.endDate, sections = arrayListOf())
    }

}