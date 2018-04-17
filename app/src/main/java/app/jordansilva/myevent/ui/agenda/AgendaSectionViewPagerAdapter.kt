package app.jordansilva.myevent.ui.agenda.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import app.jordansilva.myevent.model.AgendaSectionView
import app.jordansilva.myevent.ui.agenda.TalkListFragment

class AgendaSectionViewPagerAdapter(fragmentManager: FragmentManager,
                                    val data: List<AgendaSectionView>) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return TalkListFragment.newInstance(ArrayList(data[position].talks))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data[position].name
    }

    override fun getCount(): Int {
        return data.size
    }



}