package com.joaquinalvidrez.tecgurusfirstapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.joaquinalvidrez.tecgurusfirstapp.R
import com.joaquinalvidrez.tecgurusfirstapp.fragment.CarListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseDatabase.getInstance()

        supportFragmentManager.beginTransaction().replace(frame_layout_main.id, CarListFragment()).commit()

//        db.reference.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError?) {
//
//            }
//
//            override fun onDataChange(p0: DataSnapshot?) {
//            }
//        })



//        button.setOnClickListener {
//
//            //db.getReference().setValue()
//            db.reference.setValue(Car(editTextName.text.toString(), editTextColor.text.toString()))
//            Toast.makeText(this, db.reference.toString(), Toast.LENGTH_SHORT).show()
//
//        }

        //Log.d(javaClass.branch, FirebaseDatabase.getInstance().reference.toString())


    }


}
