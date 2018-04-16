package app.jordansilva.myevent.model

import java.util.*

data class AgendaSectionView(val id: String,
                             val name: String,
                             var talks: List<TalkView>,
                             val startDate: Date,
                             val endDate: Date)