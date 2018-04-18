package app.jordansilva.myevent.mapper

import app.jordansilva.domain.domain.Talk
import app.jordansilva.myevent.model.TalkView

class TalkViewMapper : MapperView<Talk, TalkView> {

    override fun mapToView(type: Talk): TalkView {
        return TalkView(id = type.id, title = type.name,
                startDate = type.startDate.toLocalDateTime(),
                endDate = type.endDate.toLocalDateTime(),
                locations = type.locations?.map { it })
    }

}