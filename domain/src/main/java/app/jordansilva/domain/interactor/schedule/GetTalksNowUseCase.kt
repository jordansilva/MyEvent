package app.jordansilva.domain.interactor.schedule

import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.repository.AgendaRepository

class GetTalksNowUseCase(private var agendaRepository: AgendaRepository) : BaseUseCase() {

    suspend fun execute(): List<Talk>? {
        try {
            val talks = async { agendaRepository.getTalksNow() }.await()

            return talks
        } catch (exception: Exception) {
            throw exception
        }
    }

}