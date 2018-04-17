package app.jordansilva.data.repository.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import app.jordansilva.data.model.SectionModel

@Dao
interface SectionDao : BaseDao<SectionModel> {

    @Query("SELECT * FROM sections WHERE id = :id")
    fun getSectionById(id: String): SectionModel?

    @Query("DELETE FROM sections WHERE id = :id")
    fun deleteSectionById(id: String): Int

}