package app.jordansilva.myevent.model

data class AgendaSectionView(var id: String = "",
                             var name: String,
                             var talks: List<TalkView>)