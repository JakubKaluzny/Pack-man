package com.example.pack_man

import android.app.Activity
import android.content.Context
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

    fun goToNewTripDataActivity(view: View) {
        val intent = Intent(this, TripDataActivity::class.java)
        setPreference("NOWY_WYJAZD", "TAK")
        startActivity(intent)
    }


    fun goToLastTripDataActivity(view: View) {
        val intent = Intent(this, ExpandableListActivity::class.java)
        setPreference("NOWY_WYJAZD", "NIE")
        startActivity(intent)
    }


    fun setPreference(key: String?, value: String?){
        val sharedPref = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putString(key, value)
            .apply()
    }

}