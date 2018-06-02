package com.joaquinalvidrez.tecgurusfirstapp.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.joaquinalvidrez.tecgurusfirstapp.R
import com.joaquinalvidrez.tecgurusfirstapp.activity.CarDetailActivity
import com.joaquinalvidrez.tecgurusfirstapp.adapter.CarAdapter
import com.joaquinalvidrez.tecgurusfirstapp.model.Car
import kotlinx.android.synthetic.main.fragment_car_list.*

const val BUNDLE_CAR_KEY = "Car_key"

class CarListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =// Inflate the layout for this fragment
            inflater.inflate(R.layout.fragment_car_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cars = ArrayList<Car>()

        val carAdapter = CarAdapter(cars, object : CarAdapter.OnCarClickListener {
            override fun onCarClick(car: Car, position: Int) {
                startActivity(Intent(
                        activity,
                        CarDetailActivity::class.java).apply { putExtra(BUNDLE_CAR_KEY, car.key) })
            }

        })
        FirebaseDatabase.getInstance().getReference("Carros").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                cars.clear()
                p0.children.forEach {
                    val car = it.getValue(Car::class.java)
                    car?.key = it.key.toString()
                    car?.let { cars.add(car) }
                    carAdapter.notifyDataSetChanged()
                }

            }

        })

        recycler_view_car_list.apply {
            adapter = carAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }


}
