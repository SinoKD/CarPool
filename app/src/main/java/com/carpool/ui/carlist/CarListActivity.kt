package com.carpool.ui.carlist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import com.carpool.R
import com.carpool.base.BaseActivity
import com.carpool.data.Error
import com.carpool.data.PlaceMark
import com.carpool.ui.carlist.fragments.listfragment.CarFragment
import com.carpool.ui.carlist.fragments.mapfragment.CarMapFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_car_list.*
import javax.inject.Inject

class CarListActivity : BaseActivity(), ICarList {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    @Inject
    lateinit var carListPresenter: CarListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_list)

        setSupportActionBar(toolbar)


        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        carListPresenter.fetchCarList()

    }

    override fun onCarListLoaded(carList: ArrayList<PlaceMark>) {
        Log.d("CarList", carList.toString())

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        if (carList != null) {
            val fragments: ArrayList<Fragment>? = ArrayList()
            fragments!!.add(CarFragment.newInstance(Gson().toJson(carList)))
            fragments.add(CarMapFragment.newInstance(Gson().toJson(carList)))
            mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, fragments)

            // Set up the ViewPager with the sections adapter.
            container.adapter = mSectionsPagerAdapter
        } else {
            Snackbar.make(findViewById(R.id.main_content), "Empty car list", Snackbar.LENGTH_SHORT)
        }
    }

    override fun onError(error: Error) {
        super.onError(error)
        Snackbar.make(findViewById(R.id.main_content), error.message!!, Snackbar.LENGTH_SHORT)

    }

    override fun onDestroy() {
        carListPresenter.onDestroy()
        super.onDestroy()
    }

    /*override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return super.supportFragmentInjector()
    }*/
}
