package app.jordansilva.domain.domain

import java.util.*

data class AgendaSection(val id : String,
                         val name: String,
                         val startDate: Date,
                         val endDate: Date,
                         var talks: List<Talk>?,
                         val parentId: String)