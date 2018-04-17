package com.unimedbh.prestador.data.repository.local

import android.arch.persistence.room.*
import com.unimedbh.prestador.data.model.UsuarioModel


@Dao
interface TalkDao {

    @Query("SELECT * FROM Users WHERE id = :userId")
    fun getUsuarioById(userId: String): UsuarioModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsuario(user: UsuarioModel)

    @Update
    fun updateUsuario(user: UsuarioModel): Int

    @Query("DELETE FROM Users WHERE id = :userId")
    fun deleteUsuarioById(userId: String): Int

    @Query("DELETE FROM Users")
    fun deleteTodosUsuarios()

}