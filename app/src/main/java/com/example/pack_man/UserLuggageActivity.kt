package com.example.pack_man

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class UserLuggageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_luggage)
    }


    fun goToListActivity(view: View) {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}