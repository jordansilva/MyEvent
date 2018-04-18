package app.jordansilva.myevent.di

import android.arch.persistence.room.RoomDatabase
import app.jordansilva.data.repository.AgendaDataRepository
import app.jordansilva.data.repository.local.AppDatabase
import app.jordansilva.data.repository.local.Assets
import app.jordansilva.data.repository.remote.GithubServiceFactory
import app.jordansilva.data.repository.remote.event.ApiEventService
import app.jordansilva.domain.domain.AgendaSection
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.schedule.GetAgendaUseCase
import app.jordansilva.domain.interactor.schedule.GetTalksByDayUseCase
import app.jordansilva.domain.interactor.schedule.GetTalksNowUseCase
import app.jordansilva.domain.interactor.synchronization.SynchronizeAgendaUseCase
import app.jordansilva.domain.repository.AgendaRepository
import app.jordansilva.infrastructure.util.factory.GsonFactory
import app.jordansilva.myevent.MainActivityViewModel
import app.jordansilva.myevent.mapper.AgendaSectionViewMapper
import app.jordansilva.myevent.mapper.MapperView
import app.jordansilva.myevent.mapper.TalkViewMapper
import app.jordansilva.myevent.model.AgendaSectionView
import app.jordansilva.myevent.model.TalkView
import app.jordansilva.myevent.ui.agenda.AgendaBySectionViewModel
import app.jordansilva.myevent.ui.daily.DailyProgrammeViewModel
import app.jordansilva.myevent.ui.happening.TalksHappeningViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

object KoinModule {

    val ViewModule = applicationContext {
        viewModel { AgendaBySectionViewModel(get(), get(), get("agendaSectionViewMapper"), get("talkViewMapper")) }
        viewModel { TalksHappeningViewModel(get(), get("talkViewMapper")) }
        viewModel { DailyProgrammeViewModel(get(), get("talkViewMapper")) }
        viewModel { MainActivityViewModel(get(), get()) }

        //Mappers
        factory("agendaSectionViewMapper") { AgendaSectionViewMapper() as MapperView<AgendaSection, AgendaSectionView> }
        factory("talkViewMapper") { TalkViewMapper() as MapperView<Talk, TalkView> }
    }

    val UseCaseModule = applicationContext {
        factory { GetAgendaUseCase(get()) }
        factory { GetTalksNowUseCase(get()) }
        factory { GetTalksByDayUseCase(get()) }
        factory { SynchronizeAgendaUseCase(get()) }

        bean { GsonFactory.getInstance() }
    }

    val RepositoryModule = applicationContext {
        bean { AppDatabase.getInstance(get()) as RoomDatabase }
        bean { Assets(get(), get()) }

        //Dao
        factory { AppDatabase.getInstance(get()).agendaDao() }
        factory { AppDatabase.getInstance(get()).sectionDao() }
        factory { AppDatabase.getInstance(get()).talkDao() }

        //Repositories
        bean { AgendaDataRepository(get(), get(), get(), get(), get()) } bind AgendaRepository::class
    }

    val ApiModule = applicationContext {
        factory { GithubServiceFactory.makeApiEventService(get()) } bind ApiEventService::class
    }


}