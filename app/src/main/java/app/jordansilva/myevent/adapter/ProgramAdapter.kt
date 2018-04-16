package app.jordansilva.myevent.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.jordansilva.myevent.BR
import app.jordansilva.myevent.R
import app.jordansilva.myevent.model.TalkView

class ProgramAdapter(val context: Context,
                     val data: List<TalkView>,
                     @LayoutRes val layout: Int = R.layout.row_talk_view) : RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding : ViewDataBinding = DataBindingUtil.inflate(layoutInflater, layout, parent, false)
        return ViewHolder(itemBinding)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bindView(item)
    }


    class ViewHolder(val view: ViewDataBinding) : RecyclerView.ViewHolder(view.root) {

        fun bindView(item: TalkView) {
            view.setVariable(BR.talk, item)
//            view.textDate.text = item.period()
//            view.textTitle.text = item.title
//            view.textPlace.text = item.place
        }
    }
}