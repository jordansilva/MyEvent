package app.jordansilva.myevent.ui.agenda

import android.arch.lifecycle.MutableLiveData
import app.jordansilva.domain.domain.AgendaSection
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.schedule.GetAgendaUseCase
import app.jordansilva.domain.interactor.schedule.GetTalksByDayUseCase
import app.jordansilva.infrastructure.util.Constants
import app.jordansilva.myevent.mapper.MapperView
import app.jordansilva.myevent.model.AgendaSectionView
import app.jordansilva.myevent.model.TalkView
import app.jordansilva.myevent.ui.BaseViewModel
import org.threeten.bp.OffsetDateTime
import java.util.*

class AgendaBySectionViewModel(private val getAgendaUseCase: GetAgendaUseCase,
                               private val getTalksByDayUseCase: GetTalksByDayUseCase,
                               private val mapperAgenda: MapperView<AgendaSection, AgendaSectionView>,
                               private val mapperTalk: MapperView<Talk, TalkView>) : BaseViewModel() {

    var schedule: MutableLiveData<List<AgendaSectionView>> = MutableLiveData()
    private var mode: GroupMode = GroupMode.SECTION

    enum class GroupMode {
        SECTION, PLACE
    }

    init {
        getSchedule()
    }

    fun getSchedule() {
        when (mode) {
            GroupMode.SECTION -> getScheduleBySection()
            GroupMode.PLACE -> getScheduleByLocation()
        }
    }

    private fun getScheduleBySection() {
        launchAsync {
            val agenda = getAgendaUseCase.execute()
            val list = agenda?.sections?.map {
                val item = mapperAgenda.mapToView(it)
                val talks = it.talks?.map { talk -> mapperTalk.mapToView(talk) }
                item.talks = talks ?: arrayListOf()
                item
            }

            schedule.postValue(list)
        }
    }

    private fun getScheduleByLocation() {
        launchAsync {

            val date = OffsetDateTime.now(Constants.SETTINGS.ZONEID).withDayOfMonth(18)
                    .withMonth(9)
                    .withYear(2018)
                    .withHour(12)

            val talks = getTalksByDayUseCase.execute(date)

            val hashLocation = HashMap<String, ArrayList<TalkView>>()
            talks?.forEach { talk ->
                talk.locations?.forEach {
                    if (!hashLocation.containsKey(it))
                        hashLocation[it] = arrayListOf()

                    hashLocation[it]?.add(mapperTalk.mapToView(talk))
                }
            }

            val list = hashLocation.keys.map { AgendaSectionView(name = it, talks = hashLocation[it]!!) }
            schedule.postValue(list)
        }
    }

    fun setGroupMode(value: GroupMode) {
        if (mode != value) {
            mode = value
            getSchedule()
        }
    }
}