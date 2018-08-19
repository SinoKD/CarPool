package com.carpool.ui.carlist

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding
 * B [ArrayList]  List of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager, private var fragments: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return this.fragments[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}