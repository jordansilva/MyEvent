package app.jordansilva.data.repository

import app.jordansilva.data.mapper.AgendaWithSectionsMapper
import app.jordansilva.data.mapper.TalkMapper
import app.jordansilva.data.mapper.TimeslotMapper
import app.jordansilva.data.repository.local.AgendaDao
import app.jordansilva.data.repository.local.Assets
import app.jordansilva.data.repository.local.SectionDao
import app.jordansilva.data.repository.local.TalkDao
import app.jordansilva.data.repository.remote.event.ApiEventService
import app.jordansilva.data.repository.remote.event.model.PlaceResponse
import app.jordansilva.data.repository.remote.event.model.TimeslotResponse
import app.jordansilva.domain.domain.Agenda
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.repository.AgendaRepository
import app.jordansilva.infrastructure.util.Constants
import org.threeten.bp.OffsetDateTime
import java.util.*

class AgendaDataRepository constructor(private val agendaDao: AgendaDao,
                                       private val sectionDao: SectionDao,
                                       private val talkDao: TalkDao,
                                       private val apiEventService: ApiEventService,
                                       private val assets: Assets) : AgendaRepository {


    override fun initAgenda() : Boolean {
        try {
            val places = assets.readPlaces()
            val timeslots = assets.readTimeslots()

            return initializeAgenda(places, timeslots)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return false
    }

    override suspend fun syncAgenda(): Boolean {
        try {
            val places = apiEventService.getPlaces().await()
            val timeslots = apiEventService.getTimeslots().await()

            initializeAgenda(places, timeslots)

            return true
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return false
    }

    private fun initializeAgenda(places: List<PlaceResponse>, agenda: List<TimeslotResponse>): Boolean {
        try {
            val timeslots = agenda.sortedWith(compareBy(TimeslotResponse::start, TimeslotResponse::end))

            //Agenda
            val agendas = timeslots.filter { it.parent.isNullOrEmpty() }.map(TimeslotMapper::toAgenda)
            var parentIds = agendas.map { it.id }

            //Sections
            val sections = timeslots.filter { parentIds.contains(it.parent) }.map(TimeslotMapper::toAgendaSection)
            parentIds = sections.map { it.id }

            //Places
            val hashMapPlaces = HashMap<String, PlaceResponse>()
            places.forEach { hashMapPlaces[it.id] = it }

            //Talks
            val talks = timeslots.filter { parentIds.contains(it.parent) }.map {
                it.start = it.start.atZoneSameInstant(Constants.SETTINGS.ZONEID).toOffsetDateTime()
                it.end = it.end.atZoneSameInstant(Constants.SETTINGS.ZONEID).toOffsetDateTime()
                TimeslotMapper.toTalk(it, hashMapPlaces)
            }

            agendaDao.insert(agendas)
            sectionDao.insert(sections)
            talkDao.insert(talks)
            return true
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return false
    }

    /** Get a list of [Talk] by date ignoring the hour of the day */
    override fun getTalksByDate(date: OffsetDateTime): List<Talk> {
        val talks = talkDao.getTalksByDay(date)
        return talks.map { TalkMapper.mapToDomain(it) }
    }

    /**
     * Get a list of [Talk] that are happening now.
     * The date of the event was fixed, thus, timezone will affect only the hour of the day.
     * To this end, talks are filtered by current datetime.
     */
    override fun getTalksNow(): List<Talk> {
        val date = OffsetDateTime.now(Constants.SETTINGS.ZONEID).withDayOfMonth(18).withMonth(9).withYear(2018)
        val talks = talkDao.getTalksHappeningNow(date)
        return talks.map { TalkMapper.mapToDomain(it) }
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