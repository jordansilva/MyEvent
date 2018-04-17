package app.jordansilva.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class SectionModelWithTalks : BaseModel() {
    @Embedded
    var section: SectionModel? = null

    @Relation(parentColumn = "id", entityColumn = "sectionId")
    var talks: List<TalkModel> = arrayListOf()
}
