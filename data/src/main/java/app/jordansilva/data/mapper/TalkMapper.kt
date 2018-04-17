package app.jordansilva.data.mapper

import app.jordansilva.data.model.TalkModel
import app.jordansilva.domain.domain.Talk

object TalkMapper : Mapper<Talk, TalkModel> {

    override fun mapFromDomain(type: Talk): TalkModel {
        return TalkModel(id = type.id, name = type.name,
                startDate = type.startDate, endDate = type.endDate, sectionId = type.parentId,
                locations = arrayListOf())
    }

    override fun mapToDomain(type: TalkModel): Talk {
        return Talk(id = type.id, name = type.name,
                startDate = type.startDate, endDate = type.endDate, parentId = type.sectionId,
                locations= arrayListOf())
    }

}