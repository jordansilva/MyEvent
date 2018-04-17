package app.jordansilva.myevent.ui.agenda

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import app.jordansilva.myevent.R
import app.jordansilva.myevent.model.TalkView
import kotlinx.android.synthetic.main.recyclerview_vertical.view.*
import org.jetbrains.anko.support.v4.ctx

class TalkListFragment : Fragment() {

    private var data: ArrayList<TalkView>? = null
    //private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelableArrayList(ARG_DATA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recyclerview_vertical, container, false)
        init(view)

        return view
    }

    fun init(view: View) {
        view.recyclerView.isVisible = data?.isNotEmpty() ?: false
        view.textEmpty.isVisible = !view.recyclerView.isVisible

        data?.let {
            view.recyclerView.adapter = TalkAdapter(ctx, it)
        }
    }

    companion object {

        private const val ARG_DATA = "data"

        @JvmStatic
        fun newInstance(data: ArrayList<TalkView>) =
                TalkListFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_DATA, data)
                    }
                }
    }
}
