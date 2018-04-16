package app.jordansilva.myevent.mapper

import app.jordansilva.domain.domain.AgendaSection
import app.jordansilva.myevent.model.AgendaSectionView

class AgendaSectionViewMapper : MapperView<AgendaSection, AgendaSectionView> {

    override fun mapToView(type: AgendaSection): AgendaSectionView {
        return AgendaSectionView(id = type.id, name = type.name, startDate = type.startDate, endDate = type.endDate, talks = arrayListOf())
    }

}