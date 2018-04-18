package app.jordansilva.domain.interactor.schedule

import app.jordansilva.domain.domain.Agenda
import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.repository.AgendaRepository

class GetAgendaUseCase(private var agendaRepository: AgendaRepository) : BaseUseCase() {

    suspend fun execute(): Agenda? {
        try {
            val agendas = async { agendaRepository.getAgenda() }.await()

            return if (agendas.isNotEmpty()) agendas[0] else null
        } catch (exception: Exception) {
            throw exception
        }
    }

}