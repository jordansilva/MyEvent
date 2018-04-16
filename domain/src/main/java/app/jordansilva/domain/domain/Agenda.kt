package app.jordansilva.domain.domain

import java.util.*

data class Agenda(val id : String,
                  val name: String,
                  val startDate: Date,
                  val endDate: Date,
                  var sections: List<AgendaSection>?)