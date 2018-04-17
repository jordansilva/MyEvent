package app.jordansilva.myevent.ui.daily

import android.arch.lifecycle.MutableLiveData
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.schedule.GetTalksByDayUseCase
import app.jordansilva.infrastructure.util.Constants
import app.jordansilva.myevent.mapper.MapperView
import app.jordansilva.myevent.model.TalkView
import app.jordansilva.myevent.ui.BaseViewModel
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import java.util.*

class DailyProgrammeViewModel(private val getTalksByDayUseCase: GetTalksByDayUseCase,
                              private val mapperTalk: MapperView<Talk, TalkView>) : BaseViewModel() {

    var talks: MutableLiveData<List<TalkView>> = MutableLiveData()

    init {
        getDailyTalks()
    }

    fun getDailyTalks() {
        launchAsync {
            try {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.DAY_OF_MONTH, 18)
                calendar.set(Calendar.MONTH, 8)
                calendar.set(Calendar.YEAR, 2018)
                calendar.set(Calendar.HOUR_OF_DAY, 12)

                val offsetDateTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(calendar.timeInMillis), Constants.SETTINGS.ZONEID)

                val listOfTalks = getTalksByDayUseCase.execute(offsetDateTime)
                val talksView = listOfTalks?.map { talk -> mapperTalk.mapToView(talk) }

                talks.postValue(talksView)
            } catch (ex : Exception) {
                ex.printStackTrace()
            }
        }
    }

}