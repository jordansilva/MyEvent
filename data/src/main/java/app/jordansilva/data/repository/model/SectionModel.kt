package app.jordansilva.data.repository.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.unimedbh.prestador.data.model.BaseModel
import java.util.*

@Entity(tableName = "sections")
data class AgendaSectionModel(@PrimaryKey var id: String,
                              var name: String,
                              var startDate: Date,
                              var endDate: Date,
                              var talks: ArrayList<TalkModel>?,
                              var parentId: String) : BaseModel()