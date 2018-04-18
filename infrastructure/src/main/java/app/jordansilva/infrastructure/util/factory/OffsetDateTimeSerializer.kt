package app.jordansilva.infrastructure.util.factory

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type

class OffsetDateTimeSerializer : JsonDeserializer<OffsetDateTime> {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): OffsetDateTime {
        return  OffsetDateTime.parse(json.asString)
    }

}