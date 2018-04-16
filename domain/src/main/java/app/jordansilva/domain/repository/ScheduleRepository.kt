package app.jordansilva.domain.repository

import app.jordansilva.domain.domain.Agenda
import app.jordansilva.domain.domain.Talk
import java.util.*

interface ScheduleRepository {

    fun getAgenda() : List<Agenda>
    fun getTalksByDate(date: Date): List<Talk>

}