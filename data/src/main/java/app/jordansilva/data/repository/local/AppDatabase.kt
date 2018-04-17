package app.jordansilva.data.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import app.jordansilva.data.model.AgendaModel
import app.jordansilva.data.model.SectionModel
import app.jordansilva.data.model.TalkModel
import app.jordansilva.data.repository.local.converter.RoomTypeConverters

@Database(entities = arrayOf(TalkModel::class, SectionModel::class, AgendaModel::class), version = 1)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun talkDao(): TalkDao
    abstract fun sectionDao(): SectionDao
    abstract fun agendaDao(): AgendaDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "myevent.db").build()
                }
                return INSTANCE!!
            }
        }
    }
}