package com.example.pack_man

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ListTesting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_testing)
    }

    fun goToExpandableList(view: View){
        val intent = Intent(this, ExpandableListActivity::class.java)
        startActivity(intent)
    }
}