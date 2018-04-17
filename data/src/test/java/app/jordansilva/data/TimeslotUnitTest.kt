package app.jordansilva.data

import app.jordansilva.data.repository.remote.event.model.Timeslot
import app.jordansilva.infrastructure.util.extensions.fromJson
import app.jordansilva.infrastructure.util.factory.ESTDateSerializer
import app.jordansilva.infrastructure.util.factory.GsonFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import java.text.SimpleDateFormat
import java.util.*


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

        json = "[{\"_id\":\"55872fe0700964b6509389d0\",\"end\":\"2018-09-18T23:59:00.000Z\",\"locations\":[],\"name\":\"Tuesday\",\"start\":\"2018-09-18T00:00:00.000Z\"},{\"_id\":\"55872fe0700964b650938a02\",\"end\":\"2018-09-18T17:30:00.000Z\",\"locations\":[],\"name\":\"Afternoon\",\"parent\":\"55872fe0700964b6509389d0\",\"start\":\"2018-09-18T12:30:00.000Z\"},{\"_id\":\"55872fe0700964b650938a14\",\"end\":\"2018-09-18T11:00:00.000Z\",\"locations\":[],\"name\":\"Poster Presentation 2\",\"parent\":\"55872fe0700964b650938a1c\",\"start\":\"2018-09-18T10:30:00.000Z\"},{\"_id\":\"55872fe0700964b650938a15\",\"end\":\"2018-09-18T10:30:00.000Z\",\"locations\":[],\"name\":\"Poster Presentation 1\",\"parent\":\"55872fe0700964b650938a1c\",\"start\":\"2018-09-18T10:00:00.000Z\"},{\"_id\":\"55872fe0700964b650938a18\",\"end\":\"2018-09-18T09:00:00.000Z\",\"locations\":[],\"name\":\"Presentation Name 1\",\"parent\":\"59d245d24a1b43e23845eb1f\",\"start\":\"2018-09-18T08:30:00.000Z\"},{\"_id\":\"55872fe0700964b650938a19\",\"end\":\"2018-09-18T10:00:00.000Z\",\"locations\":[],\"name\":\"Presentation Name 3\",\"parent\":\"59d245d24a1b43e23845eb1f\",\"start\":\"2018-09-18T09:30:00.000Z\"},{\"_id\":\"55872fe0700964b650938a1a\",\"end\":\"2018-09-18T09:30:00.000Z\",\"locations\":[],\"name\":\"Presentation Name 2\",\"parent\":\"59d245d24a1b43e23845eb1f\",\"start\":\"2018-09-18T09:00:00.000Z\"},{\"_id\":\"55872fe0700964b650938a1b\",\"end\":\"2018-09-18T12:30:00.000Z\",\"locations\":[{\"_id\":\"55872fe0700964b650938a08\",\"ordering\":0}],\"name\":\"Workshop\",\"parent\":\"55872fe0700964b650938a2f\",\"start\":\"2018-09-18T11:30:00.000Z\"},{\"_id\":\"55872fe0700964b650938a1c\",\"end\":\"2018-09-18T11:30:00.000Z\",\"locations\":[{\"_id\":\"55872fe0700964b650938a04\",\"ordering\":0}],\"name\":\"Poster Session\",\"parent\":\"5a044579f6699387094f370f\",\"start\":\"2018-09-18T10:00:00.000Z\"},{\"_id\":\"55872fe0700964b650938a20\",\"end\":\"2018-09-18T17:30:00.000Z\",\"locations\":[{\"_id\":\"55872fe0700964b650938a03\",\"ordering\":0}],\"name\":\"Session 3\",\"parent\":\"55872fe0700964b650938a02\",\"start\":\"2018-09-18T14:30:00.000Z\"},{\"_id\":\"55872fe0700964b650938a21\",\"end\":\"2018-09-18T14:00:00.000Z\",\"locations\":[{\"_id\":\"55872fe0700964b6509389f7\",\"ordering\":0}],\"name\":\"Session 2\",\"parent\":\"55872fe0700964b650938a02\",\"start\":\"2018-09-18T12:30:00.000Z\"},{\"_id\":\"55872fe0700964b650938a26\",\"end\":\"2018-09-18T11:30:00.000Z\",\"locations\":[],\"name\":\"Poster Presentation 3\",\"parent\":\"55872fe0700964b650938a1c\",\"start\":\"2018-09-18T11:00:00.000Z\"},{\"_id\":\"55872fe0700964b650938a2f\",\"end\":\"2018-09-18T12:00:00.000Z\",\"locations\":[],\"name\":\"Morning\",\"parent\":\"55872fe0700964b6509389d0\",\"start\":\"2018-09-18T07:15:00.000Z\"},{\"_id\":\"56c312fd87fed15b422d3395\",\"end\":\"2018-09-18T11:00:00.000Z\",\"locations\":[{\"_id\":\"55872fe0700964b6509389da\",\"ordering\":0}],\"name\":\"Plenary Session\",\"parent\":\"55872fe0700964b650938a2f\",\"start\":\"2018-09-18T10:00:00.000Z\"},{\"_id\":\"59706b0ace44926a20a490fe\",\"end\":\"2018-09-18T12:00:00.000Z\",\"locations\":[{\"_id\":\"5a044975f6699387094f374a\",\"ordering\":0}],\"name\":\"Catheter ablation for atrial fibrillation\",\"parent\":\"55872fe0700964b650938a2f\",\"start\":\"2018-09-18T11:00:00.000Z\"},{\"_id\":\"59d245d24a1b43e23845eb1f\",\"end\":\"2018-09-18T10:00:00.000Z\",\"locations\":[{\"_id\":\"55872fe0700964b650938a25\",\"ordering\":0}],\"name\":\"Abstract Session 1\",\"parent\":\"55872fe0700964b650938a2f\",\"start\":\"2018-09-18T09:00:00.000Z\"},{\"_id\":\"5a044579f6699387094f370f\",\"end\":\"2018-09-18T12:00:00.000Z\",\"locations\":[],\"name\":\"Morning Poster Sessions\",\"parent\":\"55872fe0700964b6509389d0\",\"start\":\"2018-09-18T07:00:00.000Z\"},{\"_id\":\"5a058c4cf6699387094f3d0d\",\"end\":\"2018-09-18T23:59:00.000Z\",\"locations\":[],\"name\":\"Monday\",\"start\":\"2018-09-18T00:00:00.000Z\"},{\"_id\":\"5a058c4cf6699387094f3d0e\",\"end\":\"2018-09-18T12:00:00.000Z\",\"locations\":[],\"name\":\"Morning Poster Sessions\",\"parent\":\"5a058c4cf6699387094f3d0d\",\"start\":\"2018-09-18T07:00:00.000Z\"},{\"_id\":\"5a058c4cf6699387094f3d0f\",\"end\":\"2018-09-18T12:00:00.000Z\",\"locations\":[],\"name\":\"Morning\",\"parent\":\"5a058c4cf6699387094f3d0d\",\"start\":\"2018-09-18T07:15:00.000Z\"},{\"_id\":\"5a058c4cf6699387094f3d17\",\"end\":\"2018-09-18T10:00:00.000Z\",\"locations\":[{\"_id\":\"5a05895cf6699387094f3b77\"}],\"name\":\"Abstract Session 1\",\"parent\":\"5a058c4cf6699387094f3d0f\",\"start\":\"2018-09-18T09:00:00.000Z\"},{\"_id\":\"5a058c4cf6699387094f3d18\",\"end\":\"2018-09-18T11:00:00.000Z\",\"locations\":[{\"_id\":\"5a05895cf6699387094f3b7e\"}],\"name\":\"Plenary Session\",\"parent\":\"5a058c4cf6699387094f3d0f\",\"start\":\"2018-09-18T10:00:00.000Z\"},{\"_id\":\"5a058c4cf6699387094f3d19\",\"end\":\"2018-09-18T09:00:00.000Z\",\"locations\":[],\"name\":\"Presentation Name 1\",\"parent\":\"5a058c4cf6699387094f3d17\",\"start\":\"2018-09-18T08:30:00.000Z\"},{\"_id\":\"5a058c4cf6699387094f3d1a\",\"end\":\"2017-09-18T10:00:00.000Z\",\"locations\":[],\"name\":\"Presentation Name 3\",\"parent\":\"5a058c4cf6699387094f3d17\",\"start\":\"2017-09-18T09:30:00.000Z\"},{\"_id\":\"5a058c4df6699387094f3d1b\",\"end\":\"2017-09-18T09:30:00.000Z\",\"locations\":[],\"name\":\"Presentation Name 2\",\"parent\":\"5a058c4cf6699387094f3d17\",\"start\":\"2017-09-18T09:00:00.000Z\"},{\"_id\":\"5a058c4df6699387094f3d1c\",\"end\":\"2018-09-18T12:00:00.000Z\",\"locations\":[{\"_id\":\"5a05895cf6699387094f3b7d\"}],\"name\":\"Catheter ablation for atrial fibrillation\",\"parent\":\"5a058c4cf6699387094f3d0f\",\"start\":\"2018-09-18T11:00:00.000Z\"},{\"_id\":\"5a058c4df6699387094f3d1e\",\"end\":\"2018-09-18T12:30:00.000Z\",\"locations\":[{\"_id\":\"5a05895cf6699387094f3b76\"}],\"name\":\"Workshop\",\"parent\":\"5a058c4cf6699387094f3d0f\",\"start\":\"2018-09-18T11:30:00.000Z\"},{\"_id\":\"5a058c4df6699387094f3d1f\",\"end\":\"2018-09-18T17:30:00.000Z\",\"locations\":[],\"name\":\"Afternoon\",\"parent\":\"5a058c4cf6699387094f3d0d\",\"start\":\"2018-09-18T12:30:00.000Z\"},{\"_id\":\"5a058c4df6699387094f3d20\",\"end\":\"2018-09-18T20:00:00.000Z\",\"locations\":[],\"name\":\"Keynote Session\",\"parent\":\"5a058c4cf6699387094f3d0f\",\"start\":\"2018-09-18T19:00:00.000Z\"},{\"_id\":\"5a058c4df6699387094f3d21\",\"end\":\"2017-09-18T17:30:00.000Z\",\"locations\":[{\"_id\":\"5a05895cf6699387094f3b89\"}],\"name\":\"Session 3\",\"parent\":\"5a058c4df6699387094f3d1f\",\"start\":\"2017-09-18T14:30:00.000Z\"},{\"_id\":\"5a058c4df6699387094f3d22\",\"end\":\"2017-09-18T14:00:00.000Z\",\"locations\":[{\"_id\":\"5a05895cf6699387094f3b7b\"}],\"name\":\"Session 2\",\"parent\":\"5a058c4df6699387094f3d1f\",\"start\":\"2017-09-18T12:30:00.000Z\"}]"
    }


    @Test
    fun readTimeslot_UTC() {
        val gson = GsonFactory.getInstance()

        val timeslot: ArrayList<Timeslot> = gson.fromJson(json)

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val date = "2018-09-18T23:59:00.000Z"
        val zonedDateTime = ZonedDateTime.parse(date)
        val normalDate = sdf.parse(date)

        assertEquals("20/09/2018 00:00:00", date)
    }

    @Test
    fun readTimeslot_EST() {
        val pair = Pair(Date::class.java, ESTDateSerializer())
        val gson = GsonFactory.getInstance(pair)
        val timeslot: ArrayList<Timeslot> = gson.fromJson(json)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val date = timeslot[0].start.asString(sdf)
        assertEquals("20/09/2018 00:00:00", date)
    }
}