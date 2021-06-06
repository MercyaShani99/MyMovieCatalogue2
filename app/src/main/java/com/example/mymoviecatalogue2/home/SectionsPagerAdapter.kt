package com.example.mymoviecatalogue2.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mymoviecatalogue2.R
import com.example.mymoviecatalogue2.movie.MovieFragment
import com.example.mymoviecatalogue2.tvshow.TvShowFragment

class SectionsPagerAdapter (private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.Movie, R.string.TvShow)
    }


    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment =
        when(position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int) : CharSequence? = context.resources.getString(TAB_TITLES[position])

}