package app.jordansilva.domain.interactor.schedule

import app.jordansilva.domain.domain.Agenda
import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.repository.ScheduleRepository

class GetAgendaUseCase(private var scheduleRepository: ScheduleRepository) : BaseUseCase() {

    suspend fun execute(): Agenda? {
        try {
            val agendas = async { scheduleRepository.getAgenda() }.await()

            return if (agendas.isNotEmpty()) agendas[1] else null
        } catch (exception: Exception) {
            throw exception
        }
    }

}