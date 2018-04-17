package app.jordansilva.data.repository.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.unimedbh.prestador.data.model.BaseModel
import java.util.*

@Entity(tableName = "talks")
data class AgendaDayModel(@PrimaryKey var id: String,
                          var name: String,
                          var startDate: Date,
                          var endDate: Date,
                          var sections: ArrayList<TalkModel>?) : BaseModel()
