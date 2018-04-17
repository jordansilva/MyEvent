package app.jordansilva.data.repository.local.converter

import android.arch.persistence.room.TypeConverter
import app.jordansilva.infrastructure.util.extensions.fromJson
import com.google.gson.Gson
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

object RoomTypeConverters {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let {
            return formatter.parse(value, OffsetDateTime::from)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(date: OffsetDateTime?): String? {
        return date?.format(formatter)
    }

    @TypeConverter
    @JvmStatic
    fun toListString(value: String?): ArrayList<String>? {
        return value?.let {
            return Gson().fromJson(value)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromListString(value: ArrayList<String>): String? {
        return Gson().toJson(value)
    }
}