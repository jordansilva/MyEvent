package app.jordansilva.data

import app.jordansilva.data.repository.remote.event.model.TimeslotResponse
import app.jordansilva.infrastructure.util.extensions.fromJson
import app.jordansilva.infrastructure.util.factory.GsonFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class TimeslotUnitTest {

    var json: String = ""

    @Before
    fun setUp() {
        json = "[{\"_id\":\"55872fe0700964b6509389c0\",\"end\":\"2018-09-20T00:00:00.000Z\",\"locations\":[],\"name\":\"Thursday\",\"start\":\"2018-09-20T00:00:00.000Z\"},{\"_id\":\"55872fe0700964b6509389d0\",\"end\":\"2018-09-18T23:59:00.000Z\",\"locations\":[],\"name\":\"Tuesday\",\"start\":\"2018-09-18T00:00:00.000Z\"},{\"_id\":\"55872fe0700964b6509389e8\",\"end\":\"2018-09-19T00:00:00.000Z\",\"locations\":[],\"name\":\"Wednesday\",\"start\":\"2018-09-19T00:00:00.000Z\"},{\"_id\":\"5a058c4df6699387094f3d23\",\"end\":\"2018-09-19T00:00:00.000Z\",\"locations\":[],\"name\":\"Tuesday\",\"start\":\"2018-09-19T00:00:00.000Z\"},{\"_id\":\"5a058c4cf6699387094f3d0d\",\"end\":\"2018-09-18T23:59:00.000Z\",\"locations\":[],\"name\":\"Monday\",\"start\":\"2018-09-18T00:00:00.000Z\"}]"
    }


    @Test
    fun parseTimeslots() {
        val gson = GsonFactory.getInstance()

        val timeslots: List<TimeslotResponse> = gson.fromJson(json)

        assertNotNull(timeslots)
        assertEquals(timeslots.isNotEmpty(), true)
    }

}