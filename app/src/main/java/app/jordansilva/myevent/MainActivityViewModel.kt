package app.jordansilva.myevent

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import app.jordansilva.domain.interactor.synchronization.SynchronizeAgendaUseCase
import app.jordansilva.myevent.ui.BaseAndroidViewModel
import app.jordansilva.myevent.util.extension.lastSynchronized
import java.util.*

class MainActivityViewModel(private val app: Application,
                            private val repository: SynchronizeAgendaUseCase) : BaseAndroidViewModel(app) {

    var synchronized: MutableLiveData<Boolean> = MutableLiveData()

    init {
        synchronizeAgenda()
    }

    fun synchronizeAgenda() {
        launchAsync {
            var result = false

            try {
                val isSynchronized = repository.execute(app.lastSynchronized)

                result = when (isSynchronized) {
                    true -> {
                        app.lastSynchronized = Date()
                        true
                    }
                    false -> app.lastSynchronized != null
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
                result = false
            } finally {
                synchronized.postValue(result)
            }
        }
    }

}