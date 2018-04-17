package app.jordansilva.data.repository.remote.event

import app.jordansilva.data.repository.remote.event.model.PlaceResponse
import app.jordansilva.data.repository.remote.event.model.TimeslotResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface ApiEventService {

    @GET("timeslot.json")
    fun getTimeslots(): Deferred<List<TimeslotResponse>>

    @GET("places.json")
    fun getPlaces(): Deferred<List<PlaceResponse>>

}