package app.jordansilva.data.repository.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import app.jordansilva.data.model.TalkModel
import org.threeten.bp.OffsetDateTime

@Dao
interface TalkDao : BaseDao<TalkModel> {

    @Query("SELECT * FROM talks WHERE id = :id")
    fun getTalkById(id: String): TalkModel?

    @Query("SELECT * FROM talks WHERE datetime(:date) between datetime(startDate) AND datetime(endDate) ORDER BY datetime(startDate), datetime(endDate)")
    fun getTalksHappeningNow(date: OffsetDateTime): List<TalkModel>

    @Query("SELECT * FROM talks WHERE date(:date) between date(startDate) AND date(endDate) ORDER BY datetime(startDate), datetime(endDate)")
    fun getTalksByDay(date: OffsetDateTime): List<TalkModel>

    @Query("SELECT * FROM talks WHERE sectionId = :sectionId")
    fun getTalksBySection(sectionId: String): List<TalkModel>

    @Query("SELECT * FROM talks ORDER BY datetime(startDate), datetime(endDate)")
    fun listAllTalks(): List<TalkModel>

}