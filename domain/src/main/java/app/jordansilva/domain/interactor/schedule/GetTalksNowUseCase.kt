package app.jordansilva.domain.interactor.schedule

import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.repository.ScheduleRepository
import java.util.*

class GetTalksNowUseCase(private var scheduleRepository: ScheduleRepository) : BaseUseCase() {

    suspend fun execute(date: Date): List<Talk>? {
        try {
            val talks = async { scheduleRepository.getTalksByDate(date) }.await()

            return talks
        } catch (exception: Exception) {
            throw exception
        }
    }

}