package com.unimedbh.prestador.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "talks")
data class TalkModel(@PrimaryKey var id: String,
                     var name: String,
                     var startDate: Date,
                     var endDate: Date,
                     var locations: ArrayList<String>?,
                     var parentId: String) : BaseModel()
