package app.jordansilva.data.repository.remote

import app.jordansilva.data.repository.remote.event.ApiEventService
import app.jordansilva.infrastructure.util.Constants
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object GithubServiceFactory : ServiceFactory() {

    fun makeApiEventService(gson: Gson) = makeService(Constants.API.GITHUB_URL, gson, ApiEventService::class.java)


    private fun <T> makeService(baseUrl: String, gson: Gson, obj: Class<T>): T {
        val logginInterceptor = makeLoggingInterceptor(true)
        val okHttpClient = makeOkHttpClient(logginInterceptor)

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(obj)
    }
}