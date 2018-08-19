package com.carpool.ui.carlist.fragments.listfragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.carpool.R
import com.carpool.base.BaseFragment
import com.carpool.data.PlaceMark
import com.carpool.ui.components.ItemOffsetDecoration
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * A fragment representing a list of Items.
 */
class CarFragment : BaseFragment() {

    private var carListString: String? = null
    private var carList: ArrayList<PlaceMark>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listType = object : TypeToken<List<PlaceMark>>() {}.type

        arguments?.let {
            carListString = it.getString(ARG_CAR_LIST)
            carList = Gson().fromJson(carListString, listType)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_car_recyclerview, container, false)

        // Set the adapter

        val spacing = resources.getDimensionPixelOffset(R.dimen.default_spacing_small)
        if (view is RecyclerView) {
            with(view) {
                addItemDecoration(ItemOffsetDecoration(spacing))

                if (carList != null) {
                    adapter = CarListRecyclerViewAdapter()
                    (adapter as CarListRecyclerViewAdapter).setData(carList!!)
                }

                runLayoutAnimation(this)
            }
        }
        return view
    }

    companion object {
        const val ARG_CAR_LIST = "car_list"
        @JvmStatic
        fun newInstance(carList: String) =
                CarFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_CAR_LIST, carList)
                    }
                }
    }


    /**
     * function to run the recycler view load animation
     *
     * @param recyclerView
     */
    private fun runLayoutAnimation(recyclerView: RecyclerView) {
        val context = recyclerView.context

        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down_animation)

        recyclerView.layoutAnimation = controller
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }
}
