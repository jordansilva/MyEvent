package app.jordansilva.myevent.ui.schedule

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.*
import androidx.core.view.isVisible
import app.jordansilva.myevent.R
import app.jordansilva.myevent.model.AgendaSectionView
import app.jordansilva.myevent.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import org.jetbrains.anko.support.v4.act
import org.koin.android.architecture.ext.viewModel

class DailyProgrammeFragment : BaseFragment() {

    val viewModel by viewModel<DailyProgrammeViewModel>()

    companion object {
        fun newInstance() = DailyProgrammeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        act.setTitle(R.string.title_schedule)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        viewModel.schedule.observe(this, Observer { setUpTalks(it) })
        viewModel.getScheduleAnyDate()

        configureUi(view)
        return view
    }

    private fun configureUi(view: View) {
        view.container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.tabs))
        view.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view.container))
    }

    private fun setUpTalks(data: List<AgendaSectionView>?) {
        container.isVisible = data?.isNotEmpty() ?: false
        textEmpty.isVisible = !container.isVisible

        data?.let {
            container.adapter = DailySectionViewPagerAdapter(fragmentManagerChild, it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_schedule, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
