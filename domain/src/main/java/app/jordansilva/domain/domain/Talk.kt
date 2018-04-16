package app.jordansilva.domain.domain

import java.util.*

data class Talk(val id : String,
                val name: String,
                val startDate: Date,
                val endDate: Date,
                val locations: List<Location>?,
                val parentId: String) {

    data class Location(val id: String, val name: String?)
}