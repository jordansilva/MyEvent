package app.jordansilva.myevent.ui.daily

import android.arch.lifecycle.MutableLiveData
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.schedule.GetTalksByDayUseCase
import app.jordansilva.infrastructure.util.Constants
import app.jordansilva.myevent.mapper.MapperView
import app.jordansilva.myevent.model.TalkView
import app.jordansilva.myevent.ui.BaseViewModel
import com.crashlytics.android.Crashlytics
import org.threeten.bp.OffsetDateTime

class DailyProgrammeViewModel(private val getTalksByDayUseCase: GetTalksByDayUseCase,
                              private val mapperTalk: MapperView<Talk, TalkView>) : BaseViewModel() {

    var talks: MutableLiveData<List<TalkView>> = MutableLiveData()

    init {
        getDailyTalks()
    }

    fun getDailyTalks() {
        launchAsync {
            try {
                val date = OffsetDateTime.now(Constants.SETTINGS.ZONEID)
                        .withDayOfMonth(18)
                        .withMonth(9)
                        .withYear(2018)
                        .withHour(12)

                val listOfTalks = getTalksByDayUseCase.execute(date)
                val talksView = listOfTalks?.map { talk -> mapperTalk.mapToView(talk) }

                talks.postValue(talksView)
            } catch (ex: Exception) {
                Crashlytics.logException(ex)
                ex.printStackTrace()
            }
        }
    }

}