package app.jordansilva.data.mapper

import app.jordansilva.data.model.AgendaWithSections
import app.jordansilva.domain.domain.Agenda

object AgendaWithSectionsMapper : Mapper<Agenda, AgendaWithSections> {

    override fun mapFromDomain(type: Agenda): AgendaWithSections {
        return AgendaWithSections().apply {
            agenda = AgendaMapper.mapFromDomain(type)
            sections = type.sections?.map { SectionMapper.mapFromDomain(it) } ?: arrayListOf()
        }
    }

    override fun mapToDomain(type: AgendaWithSections): Agenda {
        val agenda = AgendaMapper.mapToDomain(type.agenda!!)
        agenda.sections = type.sections.map { SectionMapper.mapToDomain(it) }
        return agenda
    }

}