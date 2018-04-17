package app.jordansilva.domain.interactor.schedule

import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.repository.AgendaRepository
import org.threeten.bp.OffsetDateTime

class GetTalksByDayUseCase(private var agendaRepository: AgendaRepository) : BaseUseCase() {

    suspend fun execute(date: OffsetDateTime): List<Talk>? {
        try {
            val list = async { agendaRepository.getTalksByDate(date) }.await()
            return list

        } catch (exception: Exception) {
            throw exception
        }
    }

}