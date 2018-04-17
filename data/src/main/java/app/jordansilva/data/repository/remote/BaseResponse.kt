package app.jordansilva.data.repository.remote

import app.jordansilva.infrastructure.util.extensions.fromJson
import com.google.gson.Gson

class BaseResponse<T> {

    var code: Int = 0
    var status: String = ""
    var message: String = ""
    var data: T? = null

    fun <D> toJson(obj: Class<D>): D? {
        data?.let {
            val gson = Gson()
            val json = gson.toJson(data)
            return gson.fromJson(json, obj)
        }

        return null
    }

    fun <D> toJsonList(obj: Class<D>): List<D> {
        data?.let {
            val gson = Gson()
            val json = gson.toJson(data)
            return gson.fromJson(json)
        }

        return arrayListOf()
    }
}