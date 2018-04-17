package com.unimedbh.prestador.data.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.unimedbh.prestador.data.model.UsuarioModel

@Database(entities = arrayOf(UsuarioModel::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UsuarioDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java,
                            "prestadorUnimed.db").build()
                }
                return INSTANCE!!
            }
        }
    }
}