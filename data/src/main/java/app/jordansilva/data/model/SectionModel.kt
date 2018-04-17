package app.jordansilva.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.OffsetDateTime
import java.util.*

@Entity(tableName = "sections",
        foreignKeys = [
            ForeignKey(onDelete = ForeignKey.CASCADE,
                    entity = AgendaModel::class,
                    parentColumns = ["id"],
                    childColumns = ["agendaId"])
        ])
data class SectionModel(@PrimaryKey var id: String,
                        var name: String,
                        var startDate: OffsetDateTime,
                        var endDate: OffsetDateTime,
                        var agendaId: String) : BaseModel() {

    @Ignore
    var talks: ArrayList<TalkModel>? = null

}