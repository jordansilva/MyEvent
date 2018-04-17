package app.jordansilva.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "agendas")
data class AgendaModel(@PrimaryKey var id: String,
                       var name: String,
                       var startDate: OffsetDateTime,
                       var endDate: OffsetDateTime) : BaseModel()
