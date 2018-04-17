package app.jordansilva.domain.domain

import org.threeten.bp.OffsetDateTime

data class Talk(val id : String,
                val name: String,
                val startDate: OffsetDateTime,
                val endDate: OffsetDateTime,
                val locations: List<String>?,
                val parentId: String)