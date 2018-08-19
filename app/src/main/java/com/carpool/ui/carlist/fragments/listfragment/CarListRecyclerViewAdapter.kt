package com.carpool.ui.carlist.fragments.listfragment


import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.carpool.R
import com.carpool.data.PlaceMark
import kotlinx.android.synthetic.main.item_car_cell.view.*

/**
 * [RecyclerView.Adapter] that can display a [PlaceMark] and makes a call to the
 */

class CarListRecyclerViewAdapter()
    : RecyclerView.Adapter<CarListRecyclerViewAdapter.ViewHolder>() {

    private lateinit var carList: ArrayList<PlaceMark>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_car_cell, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = carList[position]
        holder.tvCarName.text = item.name
        holder.tvCarVinNumber.text = "VIN - " + item.vin
        holder.tvCarEngineType.text = "Engine Type - " + item.engineType

        val exteriorId: Int
        if (item.exterior.equals("UNACCEPTABLE"))
            exteriorId = R.drawable.ic_close_red
        else
            exteriorId = R.drawable.ic_green_tick

        val interiorId = if (item.interior.equals("UNACCEPTABLE"))
            R.drawable.ic_close_red
        else
            R.drawable.ic_green_tick

        holder.tvInterior.setCompoundDrawablesWithIntrinsicBounds(interiorId, 0, 0, 0)
        holder.tvExterior.setCompoundDrawablesWithIntrinsicBounds(exteriorId, 0, 0, 0)

        holder.tvFuel.text = item.fuel.toString()
    }

    override fun getItemCount(): Int = carList.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val tvCarName: TextView = mView.tv_car_name
        val tvCarVinNumber: TextView = mView.tv_vin_number
        val tvCarEngineType: TextView = mView.tv_engine_type
        val tvExterior: TextView = mView.tv_exterior
        val tvInterior: TextView = mView.tv_interior
        val tvFuel: TextView = mView.tv_fuel


        override fun toString(): String {
            return super.toString() + " '" + tvCarVinNumber.text + "'"
        }
    }

    public fun setData(carList: ArrayList<PlaceMark>) {
        this.carList = carList
        notifyDataSetChanged()

    }
}
