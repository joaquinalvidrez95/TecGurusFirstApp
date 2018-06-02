package com.joaquinalvidrez.tecgurusfirstapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaquinalvidrez.tecgurusfirstapp.R
import com.joaquinalvidrez.tecgurusfirstapp.model.Car
import kotlinx.android.synthetic.main.item_car.view.*

class CarAdapter(private val cars: List<Car>, private val onCarClickListener: OnCarClickListener)
    : RecyclerView.Adapter<CarAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false))

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(cars[position], onCarClickListener)

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(car: Car, onCarClickListener: OnCarClickListener) {
            this.view.text_view_branch.text = "Marca: ${car.branch}"
            this.view.text_view_color.text = "Color: ${car.color}"
            this.view.text_view_model.text = "Modelo: ${car.model}"
            this.view.text_view_year.text = "Año: ${car.year}"
            view.setOnClickListener {
                onCarClickListener.onCarClick(car, adapterPosition)
            }
        }

    }

    interface OnCarClickListener {
        fun onCarClick(car: Car, position: Int)
    }
}
