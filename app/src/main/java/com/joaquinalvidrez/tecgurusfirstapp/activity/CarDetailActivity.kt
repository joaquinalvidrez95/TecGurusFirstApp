package com.joaquinalvidrez.tecgurusfirstapp.activity

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
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

                        val notificationCompat = NotificationCompat.Builder(applicationContext, "wwe")
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setContentTitle("Car selected")
                                .setAutoCancel(true)
                                .setContentText("A car was selected ${car?.branch}")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                                .notify(1, notificationCompat.build())


                        car?.let {
                            Picasso.get().load(it.url).into(image_view_car)
                            text_view_detail_branch.text = car.branch
                            text_view_detail_color.text = car.color
                            text_view_detail_model.text = car.model
                            text_view_detail_year.text = car.year.toString()
                        }

                    }
                })

        button_open_web_view.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }


    }
}