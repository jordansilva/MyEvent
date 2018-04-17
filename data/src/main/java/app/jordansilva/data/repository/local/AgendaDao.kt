package app.jordansilva.data.repository.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import app.jordansilva.data.model.AgendaModel
import app.jordansilva.data.model.AgendaWithSections
import org.threeten.bp.OffsetDateTime

@Dao
interface AgendaDao : BaseDao<AgendaModel> {

    @Query("SELECT * FROM agendas WHERE id = :id")
    fun getAgendaById(id: String): AgendaModel?

    @Query("SELECT * FROM agendas ORDER BY datetime(startDate), datetime(endDate)")
    fun listAgendas(): List<AgendaModel>

    @Query("SELECT * FROM agendas WHERE date(:date) between date(startDate) AND date(endDate)")
    fun getAgendaByDay(date: OffsetDateTime): List<AgendaModel>

    @Query("SELECT * FROM agendas ORDER BY datetime(startDate), datetime(endDate)")
    fun listAgendasWithSections(): List<AgendaWithSections>

}