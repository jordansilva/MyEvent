package app.jordansilva.data.repository

import app.jordansilva.data.repository.local.Assets
import app.jordansilva.domain.domain.Agenda
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.repository.ScheduleRepository
import app.jordansilva.infrastructure.util.extensions.*
import java.util.*

class ScheduleDataRepository constructor(private val assets: Assets) : ScheduleRepository {


    /**
     * Get a list of [Talk] by date ignoring the day time
     */
    override fun getTalksByDate(date: Date): List<Talk> {
        val listOfAgenda = getAgenda()

        val agenda = listOfAgenda.find { date.isBetweenIngoreTime(it.startDate, it.endDate) }
        val listOfTalks = agenda?.sections?.map { it.talks ?: arrayListOf() } ?: arrayListOf()
        val talks = listOfTalks.flatten()


        return talks.sortedWith(compareBy(Talk::startDate, Talk::endDate))
    }

    /**
     * Get a list of [Talk] that are happening now.
     * To this end, talks are filtered by current datetime.
     */
    override fun getTalksNow(): List<Talk> {

        val listOfAgenda = getAgenda()

        val current = Calendar.getInstance().apply {
            val event = Calendar.getInstance()
            event.time = listOfAgenda[0].startDate
            set(event.year, event.month, event.dayOfMonth)
        }


        val date = current.time

        val agenda = listOfAgenda.find { date.isBetweenIngoreTime(it.startDate, it.endDate) }
        val listOfTalks = agenda?.sections?.map {
            it.talks?.filter { talkDate -> date.isBetween(talkDate.startDate, talkDate.endDate) }
                    ?: arrayListOf()
        } ?: arrayListOf()

        val talks = listOfTalks.flatten()
        return talks.sortedWith(compareBy(Talk::startDate, Talk::endDate))
    }

    /**
     * Get the entire [Agenda] from the conference.
     * [Agenda] represents an event day.
     * @return This method returns a list of [Agenda].
     */
    override fun getAgenda(): List<Agenda> {
        val places = assets.readPlaces()
        val agenda = assets.readTimeslots(places)

        return agenda
    }


}