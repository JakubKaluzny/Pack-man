package com.example.pack_man

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class TripDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_data)
    }

    fun setPreference(key: String?, value: String?){
        val sharedPref = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putString(key, value)
            .apply()
    }


    fun goToListActivity(view: View) {
        val inputline = findViewById<EditText>(R.id.dlugoscWyjazduID)
        setPreference("DLUGOSC_WYJAZDU", inputline.text.toString())
        val intent = Intent(this, ExpandableListActivity::class.java)
        startActivity(intent)
    }
}