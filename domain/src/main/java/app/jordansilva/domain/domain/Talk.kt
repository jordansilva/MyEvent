package app.jordansilva.domain.domain

import org.threeten.bp.OffsetDateTime

data class Talk(val id : String,
                val name: String,
                val startDate: OffsetDateTime,
                val endDate: OffsetDateTime,
                val locations: List<Location>?,
                val parentId: String) {

    data class Location(val id: String, val name: String?)
}