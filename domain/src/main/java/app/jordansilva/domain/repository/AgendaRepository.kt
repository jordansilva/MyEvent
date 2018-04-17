package app.jordansilva.domain.repository

import app.jordansilva.domain.domain.Agenda
import app.jordansilva.domain.domain.Talk
import org.threeten.bp.OffsetDateTime

interface AgendaRepository {

    fun getAgenda() : List<Agenda>
    fun getTalksByDate(date: OffsetDateTime): List<Talk>
    fun getTalksNow(): List<Talk>

}