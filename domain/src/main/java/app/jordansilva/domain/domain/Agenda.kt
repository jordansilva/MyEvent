package app.jordansilva.domain.domain

import org.threeten.bp.OffsetDateTime

data class Agenda(val id : String,
                  val name: String,
                  val startDate: OffsetDateTime,
                  val endDate: OffsetDateTime,
                  var sections: List<AgendaSection>?)