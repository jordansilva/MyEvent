package app.jordansilva.myevent

import android.app.Application
import app.jordansilva.myevent.di.KoinModule
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.jakewharton.threetenabp.AndroidThreeTen
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.startKoin



class MyEventApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics(), Answers())
        //InternetUtil.init(this)
        AndroidThreeTen.init(this)

        startKoin(this, listOf(KoinModule.UseCaseModule,
                KoinModule.ViewModule,
                KoinModule.RepositoryModule,
                KoinModule.ApiModule))
    }
}