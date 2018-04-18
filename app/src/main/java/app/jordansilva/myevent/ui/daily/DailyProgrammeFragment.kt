package app.jordansilva.myevent.ui.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import app.jordansilva.myevent.R
import app.jordansilva.myevent.model.TalkView
import app.jordansilva.myevent.ui.BaseFragment
import app.jordansilva.myevent.ui.agenda.TalkAdapter
import kotlinx.android.synthetic.main.recyclerview_vertical.view.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import org.koin.android.architecture.ext.viewModel

class DailyProgrammeFragment : BaseFragment() {

    private val viewModel by viewModel<DailyProgrammeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recyclerview_vertical, container, false)
        act.setTitle(R.string.title_daily_programme)

        viewModel.talks.observeForever({ setUpTalks(view, it) })

        return view
    }

    private fun setUpTalks(view: View, data: List<TalkView>?) {
        view.recyclerView.isVisible = data?.isNotEmpty() ?: false
        view.textEmpty.isVisible = !view.recyclerView.isVisible

        data?.let {
            view.recyclerView.adapter = TalkAdapter(ctx, it, R.layout.row_talk_view_2)
        }
    }

    companion object {
        fun newInstance() = DailyProgrammeFragment()
    }
}
