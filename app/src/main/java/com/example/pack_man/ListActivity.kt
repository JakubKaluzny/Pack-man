package com.example.pack_man

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val przeniesionyText = getPreference("DLUGOSC_WYJAZDU")
//        findViewById<TextView>(R.id.testText).apply { text = przeniesionyText }

    }

    fun getPreference(key: String?): String?{
        val sharedPref = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE)
        return sharedPref.getString(key, null)
    }

    fun goToUserLuggageActivity(view: View) {
        val intent = Intent(this,UserLuggageActivity::class.java)
        startActivity(intent)
    }


}