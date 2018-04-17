package app.jordansilva.data.mapper

import app.jordansilva.data.model.SectionModel
import app.jordansilva.domain.domain.AgendaSection

object SectionMapper : Mapper<AgendaSection, SectionModel> {

    override fun mapFromDomain(type: AgendaSection): SectionModel {
        return SectionModel(id = type.id,
                name = type.name,
                startDate = type.startDate,
                endDate = type.endDate,
                agendaId = type.parentId)
                .apply {
                    talks = ArrayList(type.talks?.map { TalkMapper.mapFromDomain(it) })
                }
    }

    override fun mapToDomain(type: SectionModel): AgendaSection {
        return AgendaSection(id = type.id,
                name = type.name,
                startDate = type.startDate,
                endDate = type.endDate,
                parentId = type.agendaId,
                talks = ArrayList(type.talks?.map { TalkMapper.mapToDomain(it) }))
    }

}