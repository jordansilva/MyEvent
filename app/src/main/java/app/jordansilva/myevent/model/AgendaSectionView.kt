package app.jordansilva.myevent.model

data class AgendaSectionView(val id: String,
                             val name: String,
                             var talks: List<TalkView>)