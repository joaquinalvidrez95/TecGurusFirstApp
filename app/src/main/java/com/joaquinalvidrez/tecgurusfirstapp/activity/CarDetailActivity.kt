package com.joaquinalvidrez.tecgurusfirstapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.joaquinalvidrez.tecgurusfirstapp.R
import com.joaquinalvidrez.tecgurusfirstapp.fragment.BUNDLE_CAR_KEY
import com.joaquinalvidrez.tecgurusfirstapp.model.Car
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_car_detail.*

class CarDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)
        val intent = intent.getStringExtra(BUNDLE_CAR_KEY)
        Toast.makeText(this, intent, Toast.LENGTH_SHORT).show()
        FirebaseDatabase
                .getInstance()
                .getReference("Carros//${intent}")
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val car = p0.getValue(Car::class.java)
                        car?.let {
                            Picasso.get().load(it.url).into(image_view_car)
                            text_view_detail_branch.text = car.branch
                            text_view_detail_color.text = car.color
                            text_view_detail_model.text = car.model
                            text_view_detail_year.text = car.year.toString()
                        }

                    }
                })


    }
}