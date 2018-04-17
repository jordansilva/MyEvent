package com.unimedbh.prestador.data.repository.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
            val typeToken = TypeToken.getParameterized(ArrayList::class.java, obj).type
            val json = gson.toJson(data)
            return gson.fromJson(json, typeToken)
        }

        return arrayListOf()
    }
}