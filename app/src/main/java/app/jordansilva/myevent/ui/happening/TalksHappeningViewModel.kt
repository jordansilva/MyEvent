package app.jordansilva.myevent.ui.happening

import android.arch.lifecycle.MutableLiveData
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.schedule.GetTalksNowUseCase
import app.jordansilva.myevent.mapper.MapperView
import app.jordansilva.myevent.model.TalkView
import app.jordansilva.myevent.ui.BaseViewModel
import java.util.*

class TalksHappeningViewModel(private val talksNowUseCase: GetTalksNowUseCase,
                              private val mapperTalk: MapperView<Talk, TalkView>) : BaseViewModel() {

    var talks: MutableLiveData<List<TalkView>> = MutableLiveData()

    init {
        getTalksHappeningNow()
    }

    fun getTalksHappeningNow() {
        launchAsync {
            val listOfTalks = talksNowUseCase.execute(Date())
            val talksView = listOfTalks?.map { talk -> mapperTalk.mapToView(talk) }

            talks.postValue(talksView)
        }
    }

}