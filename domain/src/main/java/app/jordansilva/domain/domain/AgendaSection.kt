package app.jordansilva.domain.domain

import org.threeten.bp.OffsetDateTime

data class AgendaSection(val id : String,
                         val name: String,
                         val startDate: OffsetDateTime,
                         val endDate: OffsetDateTime,
                         var talks: List<Talk>?,
                         val parentId: String)