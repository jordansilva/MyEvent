package app.jordansilva.myevent.ui.schedule

import android.arch.lifecycle.MutableLiveData
import app.jordansilva.domain.domain.AgendaSection
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.schedule.GetAgendaUseCase
import app.jordansilva.myevent.mapper.MapperView
import app.jordansilva.myevent.model.AgendaSectionView
import app.jordansilva.myevent.model.TalkView
import app.jordansilva.myevent.ui.BaseViewModel

class DailyProgrammeViewModel(private val agendaUseCase: GetAgendaUseCase,
                              private val mapperAgenda: MapperView<AgendaSection, AgendaSectionView>,
                              private val mapperTalk: MapperView<Talk, TalkView>) : BaseViewModel() {

    var schedule: MutableLiveData<List<AgendaSectionView>> = MutableLiveData()

    fun getScheduleAnyDate() {
        launchAsync {
            val agenda = agendaUseCase.execute()
            val list = agenda?.sections?.map {
                val item = mapperAgenda.mapToView(it)
                val talks = it.talks?.map { talk -> mapperTalk.mapToView(talk) }
                item.talks = talks ?: arrayListOf()
                item
            }

            schedule.postValue(list)
        }
    }

}