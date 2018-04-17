package app.jordansilva.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.OffsetDateTime
import java.util.*

@Entity(tableName = "talks",
        foreignKeys = [
            ForeignKey(onDelete = ForeignKey.CASCADE,
                    entity = SectionModel::class,
                    childColumns = ["sectionId"],
                    parentColumns = ["id"])
        ])
data class TalkModel(@PrimaryKey var id: String,
                     var name: String,
                     var startDate: OffsetDateTime,
                     var endDate: OffsetDateTime,
                     var locations: ArrayList<String>?,
                     var sectionId: String) : BaseModel()
