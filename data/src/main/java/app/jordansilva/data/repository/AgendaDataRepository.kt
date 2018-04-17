package app.jordansilva.data.repository

import app.jordansilva.data.mapper.AgendaWithSectionsMapper
import app.jordansilva.data.mapper.TalkMapper
import app.jordansilva.data.mapper.TimeslotMapper
import app.jordansilva.data.repository.local.AgendaDao
import app.jordansilva.data.repository.local.SectionDao
import app.jordansilva.data.repository.local.TalkDao
import app.jordansilva.data.repository.remote.event.ApiEventService
import app.jordansilva.data.repository.remote.event.model.PlaceResponse
import app.jordansilva.data.repository.remote.event.model.TimeslotResponse
import app.jordansilva.domain.domain.Agenda
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.repository.AgendaRepository
import kotlinx.coroutines.experimental.async
import org.threeten.bp.OffsetDateTime
import java.util.*

class AgendaDataRepository constructor(private val agendaDao: AgendaDao,
                                       private val sectionDao: SectionDao,
                                       private val talkDao: TalkDao,
                                       private val apiEventService: ApiEventService) : AgendaRepository {


    init {
        populateDb()
    }

    private fun populateDb() {
        async {
            try {
                val places = apiEventService.getPlaces().await()
                val hashMapPlaces = HashMap<String, PlaceResponse>()
                places.forEach { hashMapPlaces[it.id] = it }

                var timeslots = apiEventService.getTimeslots().await()
                timeslots = timeslots.sortedWith(compareBy(TimeslotResponse::start, TimeslotResponse::end))

                val agendas = timeslots.filter { it.parent.isNullOrEmpty() }.map(TimeslotMapper::toAgenda)
                var parentIds = agendas.map { it.id }

                val sections = timeslots.filter { parentIds.contains(it.parent) }.map(TimeslotMapper::toAgendaSection)
                parentIds = sections.map { it.id }

                val talks = timeslots.filter { parentIds.contains(it.parent) }.map { TimeslotMapper.toTalk(it, hashMapPlaces) }

                agendaDao.insert(agendas)
                sectionDao.insert(sections)
                talkDao.insert(talks)
            } catch (ex : Exception) {
                ex.printStackTrace()
            }
        }
    }

    /** Get a list of [Talk] by date ignoring the day time */
    override fun getTalksByDate(date: OffsetDateTime): List<Talk> {
        val talks = talkDao.getTalksByDay(date)
        return talks.map { TalkMapper.mapToDomain(it) }
    }

    /**
     * Get a list of [Talk] that are happening now.
     * To this end, talks are filtered by current datetime.
     */
    override fun getTalksNow(): List<Talk> {

        val calendar = Calendar.getInstance()
        calendar.set(2018, 9, 18)

//        val instant = Instant.ofEpochMilli(calendar.timeInMillis)
//
//        val offsetDateTime = OffsetDateTime.ofInstant()

        val talks = talkDao.getTalksHappeningNow(OffsetDateTime.now())
        return talks.map { TalkMapper.mapToDomain(it) }

//        val listOfAgenda = getAgenda()
//
//        val current = Calendar.getInstance().apply {
//            val event = Calendar.getInstance()
//            event.time = listOfAgenda[0].startDate
//            set(event.year, event.month, event.dayOfMonth)
//        }
//
//
//        val date = current.time
//
//        val agenda = listOfAgenda.find { date.isBetweenIngoreTime(it.startDate, it.endDate) }
//        val listOfTalks = agenda?.sections?.map {
//            it.talks?.filter { talkDate -> date.isBetween(talkDate.startDate, talkDate.endDate) }
//                    ?: arrayListOf()
//        } ?: arrayListOf()
//
//        val talks = listOfTalks.flatten()
//        return talks.sortedWith(compareBy(Talk::startDate, Talk::endDate))
    }

    /**
     * Get the entire [Agenda] from the conference.
     * [Agenda] represents an event day.
     * @return This method returns a list of [Agenda].
     */
    override fun getAgenda(): List<Agenda> {
        val listOfAgendas = agendaDao.listAgendasWithSections()
        return listOfAgendas.map { AgendaWithSectionsMapper.mapToDomain(it) }
    }


}