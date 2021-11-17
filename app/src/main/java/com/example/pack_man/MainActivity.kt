package com.example.pack_man

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.view.View.inflate
import android.view.LayoutInflater
//import com.example.pack_man.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun goToTripDataActivity(view: View) {
        val intent = Intent(this, TripDataActivity::class.java)
        startActivity(intent)
    }

}