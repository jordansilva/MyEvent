package app.jordansilva.myevent

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import app.jordansilva.myevent.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel


class MainActivity : AppCompatActivity() {

    val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.synchronized.observe(this, Observer {
            when (it) {
                true -> success()
                else -> failed()
            }
        })
    }

    private fun success() {
        startActivity<HomeActivity>()
        finish()
    }

    private fun failed() {
        lottieAnimation.pauseAnimation()

        val snackbar = Snackbar.make(mainContent, R.string.error_synchronized_agenda, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, { retry() })
                .setActionTextColor(ContextCompat.getColor(ctx, R.color.lime))

        val snackbarView = snackbar.view
        val textView = snackbarView.findViewById<TextView>(android.support.design.R.id.snackbar_text)

        snackbarView.setBackgroundColor(ContextCompat.getColor(ctx, R.color.purple))
        textView.setTextColor(Color.WHITE)

        snackbar.show()
    }

    private fun retry() {
        viewModel.synchronizeAgenda()
    }

}
