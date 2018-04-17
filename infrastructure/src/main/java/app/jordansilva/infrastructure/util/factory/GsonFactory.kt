package app.jordansilva.infrastructure.util.factory

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type
import java.util.*

object GsonFactory {
    fun getInstance(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    fun getInstance(vararg adapters : Pair<Type, Any>): Gson {
        val builder = GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        adapters.forEach {
            if (it.second is JsonDeserializer<*>) {
                builder.registerTypeAdapter(it.first, it.second)
            }
        }

        return builder.create()
    }
}