package app.jordansilva.data.repository.local.mapper

import app.jordansilva.data.repository.local.model.Timeslot
import app.jordansilva.domain.domain.Talk

object TimeslotToTalkMapper {

    fun toTalk(timeslot: Timeslot, places: HashMap<String, String>): Talk {
        return Talk(id = timeslot.id, name = timeslot.name, startDate = timeslot.start, endDate = timeslot.end,
                parentId = timeslot.parent ?: "",
                locations = timeslot.location?.map { loc -> Talk.Location(id = loc.id, name = places[loc.id]) }
        )
    }

}