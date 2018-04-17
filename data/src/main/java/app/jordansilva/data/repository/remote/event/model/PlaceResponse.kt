package app.jordansilva.data.repository.remote.event.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
        @SerializedName("_id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("configuratorType") val configuratorType: String)