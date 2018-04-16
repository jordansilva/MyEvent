package app.jordansilva.data.repository.local

import android.content.Context
import java.nio.charset.Charset

class Assets(private val context: Context) {

    fun readFile(filename: String): String {
        val inputStream = context.assets.open(filename)
        return inputStream.bufferedReader(Charset.forName("UTF-8")).use { it.readText() }
    }

}