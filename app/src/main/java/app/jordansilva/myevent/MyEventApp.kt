package app.jordansilva.myevent

import android.app.Application
import app.jordansilva.myevent.di.KoinModule
import org.koin.android.ext.android.startKoin

class MyEventApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(KoinModule.UseCaseModule,
                KoinModule.ViewModule,
                KoinModule.RepositoryModule))
    }
}