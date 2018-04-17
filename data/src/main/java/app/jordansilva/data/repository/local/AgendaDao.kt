package com.unimedbh.prestador.data.repository.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import app.jordansilva.data.repository.model.SectionModel

@Dao
interface SectionDao {

    @Query("SELECT * FROM sections WHERE id = :id")
    fun getSectionById(id: String): SectionModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSection(item: SectionModel)

    @Query("DELETE FROM sections WHERE id = :id")
    fun deleteSectionById(id: String): Int

}