package app.jordansilva.myevent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.jordansilva.myevent.R
import app.jordansilva.myevent.ui.home.HomeActivity
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity<HomeActivity>()
        finish()
    }

}
