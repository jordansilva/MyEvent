package app.jordansilva.myevent.di

import app.jordansilva.data.repository.ScheduleDataRepository
import app.jordansilva.data.repository.local.Assets
import app.jordansilva.domain.domain.AgendaSection
import app.jordansilva.domain.domain.Talk
import app.jordansilva.domain.interactor.schedule.GetAgendaUseCase
import app.jordansilva.domain.interactor.schedule.GetTalksNowUseCase
import app.jordansilva.domain.repository.ScheduleRepository
import app.jordansilva.myevent.di.factory.GsonFactory
import app.jordansilva.myevent.mapper.AgendaSectionViewMapper
import app.jordansilva.myevent.mapper.MapperView
import app.jordansilva.myevent.mapper.TalkViewMapper
import app.jordansilva.myevent.model.AgendaSectionView
import app.jordansilva.myevent.model.TalkView
import app.jordansilva.myevent.ui.happening.TalksHappeningViewModel
import app.jordansilva.myevent.ui.schedule.DailyProgrammeViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

object KoinModule {

    val ViewModule = applicationContext {
        viewModel { DailyProgrammeViewModel(get(), get("agendaSectionViewMapper"), get("talkViewMapper")) }
        viewModel { TalksHappeningViewModel(get(), get("talkViewMapper")) }

        //Mappers
        factory("agendaSectionViewMapper") { AgendaSectionViewMapper() as MapperView<AgendaSection, AgendaSectionView> }
        factory("talkViewMapper") { TalkViewMapper() as MapperView<Talk, TalkView> }
    }

    val UseCaseModule = applicationContext {
        factory { GetAgendaUseCase(get()) }
        factory { GetTalksNowUseCase(get()) }

        bean { GsonFactory.getInstance() }
    }

    val RepositoryModule = applicationContext {
        bean { Assets(get()) }

        factory { ScheduleDataRepository(get()) } bind ScheduleRepository::class
    }

//    val ApiModule = applicationContext {
//        factory { GappServiceFactory.makeAuthService(get(), get()) } bind ApiAuthService::class
//        factory { makeGappInterceptor(get()) } bind HttpGappInterceptor::class
//    }


}