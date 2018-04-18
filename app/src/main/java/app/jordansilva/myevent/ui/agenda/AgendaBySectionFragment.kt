package app.jordansilva.myevent.ui.agenda

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.PopupMenu
import android.view.*
import androidx.core.view.isVisible
import app.jordansilva.myevent.R
import app.jordansilva.myevent.model.AgendaSectionView
import app.jordansilva.myevent.ui.BaseFragment
import app.jordansilva.myevent.util.extension.px
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import org.jetbrains.anko.support.v4.act
import org.koin.android.architecture.ext.viewModel


class AgendaBySectionFragment : BaseFragment() {

    val viewModel by viewModel<AgendaBySectionViewModel>()

    companion object {
        fun newInstance() = AgendaBySectionFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        act.setTitle(R.string.title_schedule)

        viewModel.schedule.observe(this, Observer { setUpTalks(it) })

        configureUi(view)
        return view
    }

    private fun configureUi(view: View) {
        view.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.tabs))
        view.viewPager.apply {
            clipToPadding = false
            pageMargin = 2.px
        }
        view.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view.viewPager))

    }

    private fun setUpTalks(data: List<AgendaSectionView>?) {
        viewPager.isVisible = data?.isNotEmpty() ?: false
        textEmpty.isVisible = !viewPager.isVisible

        data?.let {
            viewPager.adapter = AgendaSectionViewPagerAdapter(fragmentManagerChild, it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_schedule, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val view: View = act.findViewById(item.itemId)

        return when (item.itemId) {
            R.id.action_filter -> {
                showPopup(view)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun showPopup(v: View) {
        val popup = PopupMenu(act, v)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.popup_agenda_groups, popup.menu)
        popup.show()
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_group_places -> viewModel.setGroupMode(AgendaBySectionViewModel.GroupMode.PLACE)
                R.id.action_group_sections -> viewModel.setGroupMode(AgendaBySectionViewModel.GroupMode.SECTION)
            }
            true
        }
    }


}
