package app.jordansilva.domain.interactor.synchronization

import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.repository.AgendaRepository
import java.util.*

class SynchronizeAgendaUseCase(private var agendaRepository: AgendaRepository) : BaseUseCase() {

    suspend fun execute(param: Date?): Boolean {
        try {
            var synchronized = async { agendaRepository.syncAgenda() }.await()
            if (!synchronized && param == null) {
                synchronized = async { agendaRepository.initAgenda() }.await()
            }

            return synchronized
        } catch (exception: Exception) {
            throw exception
        }
    }

}