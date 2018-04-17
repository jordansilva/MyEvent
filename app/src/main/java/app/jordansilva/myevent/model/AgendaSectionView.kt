package app.jordansilva.myevent.model

import org.threeten.bp.OffsetDateTime

data class AgendaSectionView(val id: String,
                             val name: String,
                             var talks: List<TalkView>,
                             val startDate: OffsetDateTime,
                             val endDate: OffsetDateTime)