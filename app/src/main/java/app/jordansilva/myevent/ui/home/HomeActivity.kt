package app.jordansilva.myevent.ui.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import app.jordansilva.myevent.R
import app.jordansilva.myevent.ui.BaseActivity
import app.jordansilva.myevent.ui.agenda.AgendaBySectionFragment
import app.jordansilva.myevent.ui.daily.DailyProgrammeFragment
import app.jordansilva.myevent.ui.happening.TalksHappeningFragment
import app.jordansilva.myevent.util.extension.disableShiftMode
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var menuIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        configureUi()
        init()
    }

    fun configureUi() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setTitle("")

        navigation.disableShiftMode()
        navigation.setOnNavigationItemSelectedListener(HomeActivity@ this)
    }

    fun init() {
        if (menuIndex == 0)
            navigateTo(R.id.navigation_schedule)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return navigateTo(item.itemId)
    }

    private fun navigateTo(itemId: Int): Boolean {
        if (menuIndex == itemId)
            return true

        menuIndex = itemId

        return when (itemId) {
            R.id.navigation_daily -> showFragment(DailyProgrammeFragment.newInstance())
            R.id.navigation_happening -> showFragment(TalksHappeningFragment.newInstance())
            R.id.navigation_schedule -> showFragment(AgendaBySectionFragment.newInstance())
            else -> false
        }
    }

    private fun showFragment(fragment: Fragment): Boolean {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.contentHome, fragment)
        transaction.commitAllowingStateLoss()

        return true
    }
}
