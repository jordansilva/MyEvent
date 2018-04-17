package com.unimedbh.prestador.data.repository.remote.gapp

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.unimedbh.prestador.data.repository.remote.ServiceFactory
import com.unimedbh.prestador.data.repository.remote.gapp.authentication.ApiAuthService
import com.unimedbh.prestador.data.repository.remote.gapp.banner.ApiBannerGappService
import com.unimedbh.prestador.data.repository.remote.gapp.mensagem.ApiMensagemGappService
import com.unimedbh.prestador.data.repository.remote.gapp.noticia.ApiNoticiaGappService
import com.unimedbh.prestador.data.repository.remote.gapp.prestador.ApiPrestadorGappService
import com.unimedbh.prestador.data.repository.remote.interceptor.HttpGappInterceptor
import com.unimedbh.prestador.data.repository.remote.interceptor.HttpGappLoginInterceptor
import com.unimedbh.prestador.data.util.Constants
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provide "make" methods to create instances of [ApiAuthService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object GappServiceFactory : ServiceFactory() {

    fun makeAuthService(loginInterceptor: HttpGappLoginInterceptor, gson: Gson) =
            makeService(Constants.API.AUTHENTICATION, loginInterceptor, gson, ApiAuthService::class.java)

    fun makeMensagemService(gapInterceptor: HttpGappInterceptor, gson: Gson) =
            makeService(Constants.API.MENSAGEM, gapInterceptor, gson, ApiMensagemGappService::class.java)

    fun makePrestadorService(gapInterceptor: HttpGappInterceptor, gson: Gson) =
            makeService(Constants.API.PRESTADOR, gapInterceptor, gson, ApiPrestadorGappService::class.java)

    fun makeBannerService(gapInterceptor: HttpGappInterceptor, gson: Gson) =
            makeService(Constants.API.BANNER, gapInterceptor, gson, ApiBannerGappService::class.java)

    fun makeNoticiaService(gapInterceptor: HttpGappInterceptor, gson: Gson) =
            makeService(Constants.API.NOTICIA, gapInterceptor, gson, ApiNoticiaGappService::class.java)

    private fun <T> makeService(baseUrl: String, interceptor: Interceptor, gson: Gson, obj: Class<T>): T {
        val logginInterceptor = makeLoggingInterceptor(true)
        val okHttpClient = makeOkHttpClient(logginInterceptor, interceptor)

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(obj)
    }

}