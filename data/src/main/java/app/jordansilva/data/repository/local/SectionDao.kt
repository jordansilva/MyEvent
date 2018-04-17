package com.unimedbh.prestador.data.repository.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import app.jordansilva.data.repository.model.TalkModel
import app.jordansilva.domain.domain.Talk
import org.threeten.bp.OffsetDateTime

@Dao
interface TalkDao {

    @Query("SELECT * FROM talks WHERE id = :id")
    fun getTalkById(id: String): TalkModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTalk(talk: TalkModel)

    @Query("DELETE FROM talks WHERE id = :id")
    fun deleteTalkById(id: String): Int

    @Query("SELECT * FROM talks WHERE datetime(:date) between datetime(startDate) AND datetime(endDate)")
    fun getTalksHappeningNow(date: OffsetDateTime): List<Talk>

    @Query("SELECT * FROM talks WHERE date(:date) between date(startDate) AND date(endDate)")
    fun getTalksByDay(date: OffsetDateTime): List<Talk>

    @Query("SELECT * FROM talks")
    fun listAllTalks(date: OffsetDateTime): List<Talk>

}