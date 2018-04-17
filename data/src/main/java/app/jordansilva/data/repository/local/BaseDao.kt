package com.unimedbh.prestador.data.repository.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import app.jordansilva.data.repository.model.AgendaModel
import app.jordansilva.data.repository.model.AgendaWithSections
import app.jordansilva.domain.domain.Talk
import org.threeten.bp.OffsetDateTime

@Dao
interface AgendaDao {

    @Query("SELECT * FROM agendas WHERE id = :id")
    fun getAgendaById(id: String): AgendaModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAgenda(item: AgendaModel)

    @Query("DELETE FROM agendas WHERE id = :id")
    fun deleteAgendaById(id: String): Int

    @Query("SELECT * FROM agendas ORDER BY datetime(startDate), datetime(endDate)")
    fun listAgendas(): List<AgendaModel>

    @Query("SELECT * FROM agendas WHERE date(:date) between date(startDate) AND date(endDate)")
    fun getAgendaByDay(date: OffsetDateTime): List<Talk>

    @Query("SELECT * FROM agendas ORDER BY datetime(startDate), datetime(endDate)")
    fun listAgendasWithSections(): List<AgendaWithSections>

}