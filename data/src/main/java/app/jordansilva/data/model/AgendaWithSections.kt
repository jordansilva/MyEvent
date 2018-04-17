package app.jordansilva.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class AgendaWithSections : BaseModel() {
    @Embedded
    var agenda: AgendaModel? = null

    @Relation(parentColumn = "id", entityColumn = "agendaId", entity = SectionModel::class)
    var sections: List<SectionModelWithTalks> = arrayListOf()
}
