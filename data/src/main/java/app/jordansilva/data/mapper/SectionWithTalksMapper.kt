package app.jordansilva.data.mapper

import app.jordansilva.data.model.SectionModelWithTalks
import app.jordansilva.domain.domain.AgendaSection

object SectionWithTalksMapper : Mapper<AgendaSection, SectionModelWithTalks> {

    override fun mapFromDomain(type: AgendaSection): SectionModelWithTalks {
        return SectionModelWithTalks().apply {
            section = SectionMapper.mapFromDomain(type)
            talks = type.talks?.map { TalkMapper.mapFromDomain(it) } ?: arrayListOf()
        }
    }

    override fun mapToDomain(type: SectionModelWithTalks): AgendaSection {
        val agendaSection= SectionMapper.mapToDomain(type.section!!)
        agendaSection.talks = type.talks.map { TalkMapper.mapToDomain(it) }
        return agendaSection
    }

}