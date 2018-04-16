package app.jordansilva.myevent.ui

import android.annotation.SuppressLint
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import app.jordansilva.myevent.R
import org.jetbrains.anko.findOptional

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    val fragmentManager: FragmentManager by lazy { supportFragmentManager }

    fun setTitle(title: String) {
        val toolbar = findOptional<Toolbar>(R.id.toolbar)
        toolbar?.let {
            val toolbarTitle = findOptional<TextView>(R.id.toolbarTitle)
            toolbarTitle?.let {
                it.text = title
                toolbar.title = ""
            } ?: run { toolbar.title = title }
        }
    }

    override fun setTitle(@StringRes resId: Int) {
        setTitle(getString(resId))

    }

    protected fun addFragment(@IdRes containerViewId: Int, vararg fragment: Fragment) {
        fragmentManager.let { fm ->
            val ft = fm.beginTransaction()
            fragment.forEach { ft.add(containerViewId, it) }
            ft.commit()
        }
    }

}